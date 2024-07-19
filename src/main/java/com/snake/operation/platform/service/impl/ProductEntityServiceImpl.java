package com.snake.operation.platform.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.snake.operation.platform.mapper.ModuleEntityMapper;
import com.snake.operation.platform.mapper.ProductEntityMapper;
import com.snake.operation.platform.model.dto.ProductDTO;
import com.snake.operation.platform.model.entity.ModuleEntity;
import com.snake.operation.platform.model.entity.ProductEntity;
import com.snake.operation.platform.model.form.ProductForm;
import com.snake.operation.platform.model.queries.ProductPageEqualsQueries;
import com.snake.operation.platform.service.ProductEntityService;
import io.github.yxsnake.pisces.web.core.enums.DeletedEnum;
import io.github.yxsnake.pisces.web.core.utils.BizAssert;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import io.github.yxsnake.pisces.web.core.base.QueryFilter;
import io.github.yxsnake.pisces.web.core.framework.model.BaseFuzzyQueries;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * @author: snake
 * @create-time: 2024-07-16
 * @description:
 * @version: 1.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ProductEntityServiceImpl extends ServiceImpl<ProductEntityMapper,ProductEntity> implements ProductEntityService {

    private final ModuleEntityMapper moduleEntityMapper;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(ProductForm form) {
        long count = this.lambdaQuery().eq(ProductEntity::getDeleted, DeletedEnum.NORMAL.getValue())
                .eq(ProductEntity::getProductName, form.getProductName()).list().stream().count();
        BizAssert.isTrue("产品名称已存在",count>0);
        ProductEntity productEntity = form.convert(ProductEntity.class);
        String productId = IdWorker.getIdStr();
        productEntity.setProductId(productId);
        productEntity.setCreateTime(DateUtil.date());
        productEntity.setDeleted(DeletedEnum.NORMAL.getValue());
        this.save(productEntity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void modify(ProductForm form) {
        ProductEntity productEntity = this.getBaseMapper().selectById(form.getProductId());
        BizAssert.isTrue("产品信息不存在", Objects.isNull(productEntity));
        BeanUtils.copyProperties(form,productEntity);
        this.getBaseMapper().updateById(productEntity);
    }

    @Override
    public IPage<ProductDTO> pageList(QueryFilter<ProductPageEqualsQueries, BaseFuzzyQueries> queryFilter) {
        BizAssert.isTrue("参数不能为空",Objects.isNull(queryFilter));
        ProductPageEqualsQueries equalsQueries = queryFilter.getEqualsQueries();
        BizAssert.isTrue("equalsQueries不能为空",Objects.isNull(equalsQueries));
        IPage<ProductDTO> page = this.page(new Page<>(queryFilter.getPageNum(), queryFilter.getPageSize()),
                Wrappers.lambdaQuery(ProductEntity.class)
                        .eq(ProductEntity::getDeleted,DeletedEnum.NORMAL.getValue())
                        .eq(StrUtil.isNotBlank(equalsQueries.getProductName()),ProductEntity::getProductName,equalsQueries.getProductName())
        ).convert(item -> item.convert(ProductDTO.class));
        return page;
    }

    @Override
    public ProductDTO detail(String productId) {
        ProductEntity productEntity = this.getBaseMapper().selectById(productId);
        BizAssert.isTrue("产品信息不存在", Objects.isNull(productEntity));
        return productEntity.convert(ProductDTO.class);
    }

    @Override
    public void removeByProductId(String productId) {
        ProductEntity productEntity = this.getBaseMapper().selectById(productId);
        BizAssert.isTrue("产品信息不存在", Objects.isNull(productEntity));
        // 查询产品是否管理了模块信息
        List<ModuleEntity> moduleEntities = moduleEntityMapper.selectList(
                Wrappers.lambdaQuery(ModuleEntity.class)
                        .eq(ModuleEntity::getDeleted,DeletedEnum.NORMAL.getValue())
                        .eq(ModuleEntity::getProductId, productId)
        );
        BizAssert.isTrue("产品关联了模块信息不允许删除", CollUtil.isNotEmpty(moduleEntities));
    }


}
