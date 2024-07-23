package com.snake.operation.platform.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Maps;
import com.snake.operation.platform.mapper.SysMenuMapper;
import com.snake.operation.platform.model.entity.SysMenu;
import com.snake.operation.platform.model.entity.SysRole;
import com.snake.operation.platform.model.entity.SysRoleMenu;
import com.snake.operation.platform.model.entity.SysUserRole;
import com.snake.operation.platform.model.enums.SysMenuTypeEnum;
import com.snake.operation.platform.service.SysMenuService;
import com.snake.operation.platform.service.SysRoleMenuService;
import com.snake.operation.platform.service.SysRoleService;
import com.snake.operation.platform.service.SysUserRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author: snake
 * @create-time: 2024-07-23
 * @description:
 * @version: 1.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    private final SysRoleMenuService sysRoleMenuService;

    private final SysRoleService sysRoleService;

    private final SysUserRoleService sysUserRoleService;
    @Override
    public List<SysMenu> getMenuListByUserId(String userId) {
        List<String> roleIds = sysUserRoleService.lambdaQuery().eq(SysUserRole::getUserId, userId).list().stream()
                .map(SysUserRole::getRoleId).collect(Collectors.toList());

        List<String> menuIds = sysRoleMenuService.lambdaQuery().in(SysRoleMenu::getRoleId, roleIds).list().stream()
                .map(SysRoleMenu::getMenuId).collect(Collectors.toList());

        List<SysMenu> sysMenus = this.lambdaQuery().in(SysMenu::getMenuId, menuIds).list();

        return sysMenus;
    }

    @Override
    public Map<String, Set<String>> getButtonPermsMap(List<String> menuIds) {
        Map<String, Set<String>> buttonPermMap = Maps.newHashMap();
        List<SysMenu> list = this.lambdaQuery()
                .eq(SysMenu::getMenuType, SysMenuTypeEnum.BUTTON.getValue())
                .in(SysMenu::getParentId, menuIds).list();
        if(CollUtil.isNotEmpty(list)){
            list.stream().forEach(sysMenu -> {
                String parentId = sysMenu.getParentId();
                Set<String> perms = buttonPermMap.get(parentId);
                if(Objects.isNull(perms)){
                    perms = new HashSet<>();
                }
                String perm = sysMenu.getPerm();
                if(StrUtil.isNotBlank(perm)){
                    perms.add(perm);
                }
                buttonPermMap.put(parentId,perms);
            });
        }
        return buttonPermMap;
    }

    @Override
    public Map<String, Set<String>> getMenuRolesMap(List<String> menuIds) {
        Map<String, Set<String>> menuRoleCodesMap = Maps.newHashMap();
        // 基于菜单找到角色ID
        List<SysRoleMenu> list = sysRoleMenuService.lambdaQuery()
                .in(SysRoleMenu::getMenuId, menuIds)
                .list();
        if(CollUtil.isEmpty(list)){
            return menuRoleCodesMap;
        }
        // 得到所有角色ID
        Set<String> tempRoleList = list.stream().map(SysRoleMenu::getRoleId).collect(Collectors.toSet());
        // 查询角色信息
        Map<String, SysRole> sysRoleMap = sysRoleService.lambdaQuery().in(SysRole::getRoleId, tempRoleList)
                .list().stream().collect(Collectors.toMap(SysRole::getRoleId, row -> row));

        Map<String,Set<String>> resultMap = Maps.newHashMap();
        list.stream().forEach(sysRoleMenu -> {
            String menuId = sysRoleMenu.getMenuId();
            String roleId = sysRoleMenu.getRoleId();
            SysRole sysRole = sysRoleMap.get(roleId);
            if(Objects.isNull(sysRole)){
                return;
            }
            Set<String> roleCodeList = resultMap.get(menuId);
            if(CollUtil.isEmpty(roleCodeList)){
                roleCodeList = new HashSet<>();
            }
            roleCodeList.add(sysRole.getRoleCode());
            resultMap.put(menuId,roleCodeList);
        });
        return resultMap;
    }
}
