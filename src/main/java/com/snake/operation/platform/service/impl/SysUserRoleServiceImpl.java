package com.snake.operation.platform.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Sets;
import com.snake.operation.platform.mapper.SysRoleMapper;
import com.snake.operation.platform.mapper.SysUserRoleMapper;
import com.snake.operation.platform.model.entity.SysRole;
import com.snake.operation.platform.model.entity.SysUserRole;
import com.snake.operation.platform.service.SysUserRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
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
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {

    private final SysRoleMapper sysRoleMapper;
    @Override
    public List<SysRole> getRoleListByUserId(String userId) {
        return null;
    }

    @Override
    public Set<String> getCurrentUserRoles(String userId) {
        List<String> roleIds = this.lambdaQuery().eq(SysUserRole::getUserId, userId).list()
                .stream().map(SysUserRole::getRoleId).collect(Collectors.toList());
        if(CollUtil.isEmpty(roleIds)){
            return Sets.newHashSet();
        }

        Set<String> roleCodeList = sysRoleMapper.selectList(
                Wrappers.lambdaQuery(SysRole.class).in(SysRole::getRoleId, roleIds)
        ).stream().map(SysRole::getRoleCode).collect(Collectors.toSet());
        return roleCodeList;
    }
}
