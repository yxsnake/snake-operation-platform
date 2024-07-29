package com.snake.operation.platform.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.snake.operation.platform.model.dto.SysUserDTO;
import com.snake.operation.platform.model.entity.SysUser;
import com.snake.operation.platform.model.form.SysUserEnableOrDisableForm;
import com.snake.operation.platform.model.form.SysUserForm;
import com.snake.operation.platform.model.fuzzy.SysUserFuzzyQueries;
import com.snake.operation.platform.model.queries.SysUserPageEqualsQueries;
import io.github.yxsnake.pisces.web.core.base.QueryFilter;

public interface SysUserService extends IService<SysUser> {

    IPage<SysUserDTO> pageList(QueryFilter<SysUserPageEqualsQueries, SysUserFuzzyQueries> queryFilter);

    SysUserDTO create(SysUserForm sysUserForm);

    SysUserDTO modify(SysUserForm sysUserForm);

    SysUserDTO detail(String userId);

    SysUserDTO enableOrDisable(SysUserEnableOrDisableForm form);
}
