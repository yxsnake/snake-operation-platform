package com.snake.operation.platform.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.date.DateUtil;
import com.snake.operation.platform.model.dto.LoginDTO;
import com.snake.operation.platform.model.entity.SysUser;
import com.snake.operation.platform.model.enums.SysUserStatusEnum;
import com.snake.operation.platform.model.form.LoginForm;
import com.snake.operation.platform.service.LoginService;
import com.snake.operation.platform.service.SysUserService;
import io.github.yxsnake.pisces.web.core.utils.BizAssert;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final SysUserService sysUserService;

    @Override
    public LoginDTO login(LoginForm form) {
        SysUser sysUser = sysUserService.lambdaQuery()
                .eq(SysUser::getUsername, form.getUsername())
                .list().stream().findFirst().orElse(null);
        BizAssert.isTrue("用户名或密码错误", Objects.isNull(sysUser));
        BizAssert.isTrue("账号已停用", SysUserStatusEnum.DISABLE.getValue().equals(sysUser.getStatus()));
        BizAssert.isTrue("密码错误",!sysUser.getPassword().equals(form.getPassword()));
        StpUtil.login(sysUser.getUserId());
        String token = StpUtil.getTokenValue();
        LoginDTO loginDTO = LoginDTO.builder()
                .accessToken(token)
                .build();
        BeanUtils.copyProperties(sysUser,loginDTO);
        long tokenTimeout = StpUtil.getTokenTimeout();
        Date expires = DateUtil.date(tokenTimeout);
        loginDTO.setExpires(expires);
        return loginDTO;
    }
}
