package com.snake.operation.platform.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.snake.operation.platform.mapper.SysRoleMenuMapper;
import com.snake.operation.platform.model.entity.SysMenu;
import com.snake.operation.platform.model.entity.SysRoleMenu;
import com.snake.operation.platform.service.SysRoleMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: snake
 * @create-time: 2024-07-23
 * @description:
 * @version: 1.0
 */
@Slf4j
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements SysRoleMenuService {

    @Override
    public void removeRoleAuth(String roleId) {
        List<SysRoleMenu> list = this.lambdaQuery().eq(SysRoleMenu::getRoleId, roleId).list();
        if(CollUtil.isNotEmpty(list)){
            this.removeBatchByIds(list.stream().map(SysRoleMenu::getId).collect(Collectors.toList()));
        }
    }

    @Override
    public void roleAuthMenus(String roleId, List<SysMenu> menus) {
        if(StrUtil.isNotBlank(roleId) && CollUtil.isNotEmpty(menus)){
            List<SysRoleMenu> roleMenus = Lists.newArrayList();
            for (SysMenu menu : menus) {
                SysRoleMenu sysRoleMenu = new SysRoleMenu();
                sysRoleMenu.setId(IdWorker.getIdStr());
                sysRoleMenu.setRoleId(roleId);
                sysRoleMenu.setMenuId(menu.getMenuId());
                roleMenus.add(sysRoleMenu);
            }
            this.saveBatch(roleMenus);
        }
    }
}
