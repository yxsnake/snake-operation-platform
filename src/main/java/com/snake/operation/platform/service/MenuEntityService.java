package com.snake.operation.platform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.snake.operation.platform.model.entity.MenuEntity;
import com.snake.operation.platform.model.form.MenuForm;

/**
 * @author: snake
 * @create-time: 2024-07-16
 * @description:
 * @version: 1.0
 */
public interface MenuEntityService extends IService<MenuEntity> {

    Boolean create(MenuForm form);

    Boolean modify(MenuForm form);

    Boolean syncTenant(String tenantId);
}
