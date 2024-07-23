package com.snake.operation.platform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.snake.operation.platform.model.entity.SysMenu;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author: snake
 * @create-time: 2024-07-23
 * @description:
 * @version: 1.0
 */
public interface SysMenuService extends IService<SysMenu> {
    List<SysMenu> getMenuListByUserId(String userId);

    Map<String, Set<String>> getButtonPermsMap(List<String> menuIds);

    Map<String, Set<String>> getMenuRolesMap(List<String> menuIds);
}
