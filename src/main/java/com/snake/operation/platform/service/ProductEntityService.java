package com.snake.operation.platform.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.snake.operation.platform.model.dto.ProductDTO;
import com.snake.operation.platform.model.entity.ProductEntity;
import com.snake.operation.platform.model.form.ModuleForm;
import com.snake.operation.platform.model.form.ProductForm;
import com.snake.operation.platform.model.queries.ModulePageEqualsQueries;
import com.snake.operation.platform.model.queries.ProductPageEqualsQueries;
import io.github.yxsnake.pisces.web.core.base.QueryFilter;
import io.github.yxsnake.pisces.web.core.framework.model.BaseFuzzyQueries;

/**
 * @author: snake
 * @create-time: 2024-07-16
 * @description:
 * @version: 1.0
 */
public interface ProductEntityService extends IService<ProductEntity> {
    void create(ProductForm form);

    void modify(ProductForm form);

    IPage<ProductDTO> pageList(QueryFilter<ProductPageEqualsQueries, BaseFuzzyQueries> queryFilter);

    ProductDTO detail(String productId);
}
