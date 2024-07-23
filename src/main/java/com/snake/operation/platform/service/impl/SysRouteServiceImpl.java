package com.snake.operation.platform.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollUtil;
import com.google.common.collect.Lists;
import com.snake.operation.platform.model.dto.SysRouteDTO;
import com.snake.operation.platform.model.entity.SysMenu;
import com.snake.operation.platform.model.entity.SysRole;
import com.snake.operation.platform.model.entity.SysUserRole;
import com.snake.operation.platform.service.SysMenuService;
import com.snake.operation.platform.service.SysRouteService;
import com.snake.operation.platform.service.SysUserRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: snake
 * @create-time: 2024-07-23
 * @description:
 * @version: 1.0
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class SysRouteServiceImpl implements SysRouteService {

    private final SysMenuService sysMenuService;

    private final SysUserRoleService sysUserRoleService;
    @Override
    public List<SysRouteDTO> getAsyncRoutes() {
        List<SysRouteDTO> routes = Lists.newArrayList();
        // 获取当前用户 ID
        String userId = String.valueOf(StpUtil.getLoginId());
        // 查询用户菜单树
        List<SysMenu> menus = sysMenuService.getMenuListByUserId(userId);
        if(CollUtil.isEmpty(menus)){
            return routes;
        }
//        // 查询用户
//        List<SysRole> roles = sysUserRoleService.getRoleListByUserId(userId);
//        menus.stream().forEach(menu->{
//            routes.add(menu.convert(SysRouteDTO.class));
//        });
//        List<SysRouteDTO> treeNodes = streamToTree(routes,SysMenu.ROOT);
        return null;
    }

    private List<SysRouteDTO> streamToTree(List<SysRouteDTO> routes, String parentId) {
        List<SysRouteDTO> list = routes.stream()
                .filter(parent -> parent.getParentId().equals(parentId))
                .map(child -> {
                    child.setChildren(streamToTree(routes,child.getId()));
                    return child;
                }).collect(Collectors.toList());
        return list;
    }
}
