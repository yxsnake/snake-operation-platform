package com.snake.operation.platform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.snake.operation.platform.model.entity.SysMenu;
import com.snake.operation.platform.model.entity.SysRoleMenu;

import java.util.List;

/**
 * @author: snake
 * @create-time: 2024-07-23
 * @description:
 * @version: 1.0
 */
public interface SysRoleMenuService extends IService<SysRoleMenu> {
    void removeRoleAuth(String roleId);

    void roleAuthMenus(String roleId, List<SysMenu> menus);
}
