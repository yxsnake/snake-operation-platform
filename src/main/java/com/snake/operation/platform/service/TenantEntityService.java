package com.snake.operation.platform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.snake.operation.platform.model.entity.TenantEntity;
import com.snake.operation.platform.model.form.TenantAuditForm;
import com.snake.operation.platform.model.form.TenantForm;

/**
 * @author: snake
 * @create-time: 2024-07-16
 * @description: 租户 Service
 * @version: 1.0
 */
public interface TenantEntityService extends IService<TenantEntity> {
    Boolean create(TenantForm form);

    Boolean modify(TenantForm form);

    Boolean audit(TenantAuditForm form);

}
