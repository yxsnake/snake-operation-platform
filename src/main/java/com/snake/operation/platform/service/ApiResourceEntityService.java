package com.snake.operation.platform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.snake.operation.platform.model.entity.ApiResourceEntity;
import com.snake.operation.platform.model.form.ApiResourceForm;

/**
 * @author: snake
 * @create-time: 2024-07-16
 * @description:
 * @version: 1.0
 */
public interface ApiResourceEntityService extends IService<ApiResourceEntity> {
    void create(ApiResourceForm form);

    void modify(ApiResourceForm form);

    Boolean syncTenant(String tenantId);
}
