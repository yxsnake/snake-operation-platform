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


        long tokenTimeout = StpUtil.getTokenTimeout() * 1000;
        long timeout = DateUtil.date().getTime() + tokenTimeout;
        Date expires = new Date(timeout);
        log.info("剩余有效时间：{}",tokenTimeout);
        log.info("超时时间：{}",timeout);
        loginDTO.setExpires(expires);
        return loginDTO;
    }

    @Override
    public LoginDTO refreshToken() {
        StpUtil.checkActiveTimeout();
        StpUtil.updateLastActiveToNow();
        String tokenValue = StpUtil.getTokenValue();
        Object loginId = StpUtil.getLoginId();

        long tokenTimeout = StpUtil.getTokenTimeout() * 1000;
        long timeout = DateUtil.date().getTime() + tokenTimeout;
        log.info("剩余有效时间：{}",tokenTimeout);
        log.info("超时时间：{}",timeout);
        Date expires = new Date(timeout);

        LoginDTO loginDTO = LoginDTO.builder().accessToken(tokenValue).expires(expires).build();
        SysUser sysUser = sysUserService.lambdaQuery().eq(SysUser::getUserId,String.valueOf(loginId)).list().stream().findFirst().orElse(null);
        BeanUtils.copyProperties(sysUser,loginDTO);
        return loginDTO;
    }
}
