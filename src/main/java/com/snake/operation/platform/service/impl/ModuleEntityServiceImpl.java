package com.snake.operation.platform.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.snake.operation.platform.mapper.ModuleEntityMapper;
import com.snake.operation.platform.model.dto.ModuleDTO;
import com.snake.operation.platform.model.entity.ModuleEntity;
import com.snake.operation.platform.model.entity.ProductEntity;
import com.snake.operation.platform.model.form.ModuleForm;
import com.snake.operation.platform.model.queries.ModulePageEqualsQueries;
import com.snake.operation.platform.service.ModuleEntityService;
import com.snake.operation.platform.service.ProductEntityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import io.github.yxsnake.pisces.web.core.base.QueryFilter;
import io.github.yxsnake.pisces.web.core.framework.model.BaseFuzzyQueries;
import io.github.yxsnake.pisces.web.core.utils.BizAssert;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class ModuleEntityServiceImpl extends ServiceImpl<ModuleEntityMapper,ModuleEntity> implements ModuleEntityService {

    private final ProductEntityService productEntityService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(ModuleForm form) {
        ModuleEntity moduleEntity = form.convert(ModuleEntity.class);
        String moduleId = IdWorker.getIdStr();
        moduleEntity.setModuleId(moduleId);
        // 校验产品是否存在
        ProductEntity productEntity = productEntityService.getBaseMapper().selectById(form.getProductId());
        BizAssert.isTrue("模块所属产品不存在", Objects.isNull(productEntity));

        // 校验模块名称是否已存在
        int size = this.lambdaQuery().eq(ModuleEntity::getModuleName, form.getModuleName()).list().size();
        BizAssert.isTrue("模块名称已存在",size>0);
        this.save(moduleEntity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void modify(ModuleForm form) {
        ModuleEntity moduleEntity = this.getBaseMapper().selectById(form.getModuleId());
        BizAssert.isTrue("模块不存在", Objects.isNull(moduleEntity));
        // 校验产品是否存在
        ProductEntity productEntity = productEntityService.getBaseMapper().selectById(form.getProductId());
        BizAssert.isTrue("模块所属产品不存在", Objects.isNull(productEntity));
        BeanUtils.copyProperties(form,moduleEntity);

        // 校验模块名称是否已存在
        int size = this.lambdaQuery().eq(ModuleEntity::getModuleName, form.getModuleName())
                .ne(ModuleEntity::getModuleId,form.getModuleId())
                .list().size();
        BizAssert.isTrue("模块名称已存在",size>0);
        this.getBaseMapper().updateById(moduleEntity);
    }

    @Override
    public IPage<ModuleDTO> pageList(QueryFilter<ModulePageEqualsQueries, BaseFuzzyQueries> queryFilter) {
        BizAssert.isTrue("queryFilter不能为空",Objects.isNull(queryFilter));
        ModulePageEqualsQueries equalsQueries = queryFilter.getEqualsQueries();
        BizAssert.isTrue("equalsQueries不能为空",Objects.isNull(equalsQueries));
        IPage<ModuleDTO> page = this.page(new Page<>(queryFilter.getPageNum(), queryFilter.getPageSize()),
                Wrappers.lambdaQuery(ModuleEntity.class)
                        .eq(StrUtil.isNotBlank(equalsQueries.getModuleName()),ModuleEntity::getModuleName,equalsQueries.getModuleName())
        ).convert(item -> item.convert(ModuleDTO.class));
        return page;
    }

    @Override
    public ModuleDTO detail(String moduleId) {
        ModuleEntity moduleEntity = this.lambdaQuery().eq(ModuleEntity::getModuleId, moduleId).list().stream().findFirst().orElse(null);
        if(Objects.isNull(moduleEntity)){
            return null;
        }
        return moduleEntity.convert(ModuleDTO.class);
    }
}
