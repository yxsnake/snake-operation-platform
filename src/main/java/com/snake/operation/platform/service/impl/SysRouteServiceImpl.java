package com.snake.operation.platform.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import com.snake.operation.platform.model.dto.SysRouteDTO;
import com.snake.operation.platform.model.dto.SysRouterMetaDTO;
import com.snake.operation.platform.model.entity.SysMenu;
import com.snake.operation.platform.model.enums.SysMenuTypeEnum;
import com.snake.operation.platform.service.SysMenuService;
import com.snake.operation.platform.service.SysRouteService;
import com.snake.operation.platform.service.SysUserRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;
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
        List<String> menuIds = menus.stream().map(SysMenu::getMenuId).collect(Collectors.toList());

        // 基于菜单查询 查询菜单页面拥有的按钮权限
        Map<String, Set<String>> btnPermsMap = sysMenuService.getButtonPermsMap(menuIds);
        // 基于菜单查询 分配的角色
        Map<String, Set<String>> menuRolesMap = sysMenuService.getMenuRolesMap(menuIds);

        menus.stream().forEach(menu->{
            SysRouteDTO sysRouteDTO = new SysRouteDTO();
            String menuId = menu.getMenuId();
            sysRouteDTO.setId(menuId);
            sysRouteDTO.setParentId(menu.getParentId());
            sysRouteDTO.setPath(menu.getPath());
            if(StrUtil.isNotBlank(menu.getComponentName())){
                sysRouteDTO.setName(menu.getComponentName());
            }
            SysRouterMetaDTO metaDTO = new SysRouterMetaDTO();
            metaDTO.setTitle(menu.getMenuName());
            if(SysMenuTypeEnum.MENU.getValue().equals(menu.getMenuType())){
                metaDTO.setRank(menu.getRank());
            }
            metaDTO.setIcon(menu.getIcon());
            Set<String> auths = btnPermsMap.get(menuId);
            if(CollUtil.isNotEmpty(auths) && SysMenuTypeEnum.BUTTON.getValue().equals(menu.getMenuType())){
                metaDTO.setAuths(auths);
            }
            Set<String> roles = menuRolesMap.get(menuId);
            if(CollUtil.isNotEmpty(roles) && SysMenuTypeEnum.MENU.getValue().equals(menu.getMenuType())){
                metaDTO.setRoles(roles);
            }
            sysRouteDTO.setMeta(metaDTO);
            routes.add(sysRouteDTO);
        });
        List<SysRouteDTO> treeNodes = streamToTree(routes,SysMenu.ROOT);
        return treeNodes;
    }

    private List<SysRouteDTO> streamToTree(List<SysRouteDTO> routes, String parentId) {
        List<SysRouteDTO> list = routes.stream()
                .filter(item -> item.getParentId().equals(parentId))
                .collect(Collectors.toList());

        list = list.stream().map(item->{
            item.setChildren(streamToTree(routes,item.getId()));
            return item;
        }).collect(Collectors.toList());
        return list;
    }
}
