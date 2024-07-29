package com.snake.operation.platform.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.snake.operation.platform.model.dto.SysRoleDTO;
import com.snake.operation.platform.model.entity.SysRole;
import com.snake.operation.platform.model.form.RoleAuthForm;
import com.snake.operation.platform.model.form.SysRoleForm;
import com.snake.operation.platform.model.form.SysUserEnableOrDisableForm;
import com.snake.operation.platform.model.fuzzy.SysRoleFuzzyQueries;
import com.snake.operation.platform.model.queries.SysRolePageEqualsQueries;
import io.github.yxsnake.pisces.web.core.base.QueryFilter;

/**
 * @author: snake
 * @create-time: 2024-07-23
 * @description:
 * @version: 1.0
 */
public interface SysRoleService extends IService<SysRole> {
    IPage<SysRoleDTO> pageList(QueryFilter<SysRolePageEqualsQueries, SysRoleFuzzyQueries> queryFilter);

    SysRoleDTO create(SysRoleForm form);

    SysRoleDTO modify(SysRoleForm form);

    SysRoleDTO detail(String roleId);

    void roleAuth(RoleAuthForm form);

    SysRoleDTO removeByRoleId(String roleId);
}
