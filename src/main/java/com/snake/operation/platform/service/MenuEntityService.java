package com.snake.operation.platform.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.snake.operation.platform.model.dto.MenuDTO;
import com.snake.operation.platform.model.entity.MenuEntity;
import com.snake.operation.platform.model.form.MenuForm;
import com.snake.operation.platform.model.form.SyncTenantMenuForm;
import com.snake.operation.platform.model.queries.MenuPageEqualsQueries;
import io.github.yxsnake.pisces.web.core.base.BaseFuzzyQueries;
import io.github.yxsnake.pisces.web.core.base.QueryFilter;


/**
 * @author: snake
 * @create-time: 2024-07-16
 * @description:
 * @version: 1.0
 */
public interface MenuEntityService extends IService<MenuEntity> {

    Boolean create(MenuForm form);

    Boolean modify(MenuForm form);

    void removeByPlatformMenuId(String platformMenuId);

    Boolean syncTenant(SyncTenantMenuForm form);

    IPage<MenuDTO> pageList(QueryFilter<MenuPageEqualsQueries, BaseFuzzyQueries> queryFilter);
}
