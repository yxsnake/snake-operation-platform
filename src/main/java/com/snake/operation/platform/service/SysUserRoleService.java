package com.snake.operation.platform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.snake.operation.platform.model.entity.SysRole;
import com.snake.operation.platform.model.entity.SysUserRole;

import java.util.List;

/**
 * @author: snake
 * @create-time: 2024-07-23
 * @description:
 * @version: 1.0
 */
public interface SysUserRoleService extends IService<SysUserRole> {
    List<SysRole> getRoleListByUserId(String userId);
}
