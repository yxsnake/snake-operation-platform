package com.snake.operation.platform.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.snake.operation.platform.mapper.ProductEntityMapper;
import com.snake.operation.platform.model.dto.ProductDTO;
import com.snake.operation.platform.model.entity.ProductEntity;
import com.snake.operation.platform.model.form.ProductForm;
import com.snake.operation.platform.model.queries.ProductPageEqualsQueries;
import com.snake.operation.platform.service.ProductEntityService;
import lombok.extern.slf4j.Slf4j;
import io.github.yxsnake.pisces.web.core.base.QueryFilter;
import io.github.yxsnake.pisces.web.core.framework.model.BaseFuzzyQueries;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: snake
 * @create-time: 2024-07-16
 * @description:
 * @version: 1.0
 */
@Slf4j
@Service
public class ProductEntityServiceImpl extends ServiceImpl<ProductEntityMapper,ProductEntity> implements ProductEntityService {
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(ProductForm form) {

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void modify(ProductForm form) {

    }

    @Override
    public IPage<ProductDTO> pageList(QueryFilter<ProductPageEqualsQueries, BaseFuzzyQueries> queryFilter) {
        return null;
    }

    @Override
    public ProductDTO detail(String productId) {
        return null;
    }
}
