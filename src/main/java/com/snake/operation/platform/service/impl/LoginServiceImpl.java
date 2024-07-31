package com.snake.operation.platform.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.snake.operation.platform.model.dto.LoginDTO;
import com.snake.operation.platform.model.dto.RefreshTokenDTO;
import com.snake.operation.platform.model.entity.SysUser;
import com.snake.operation.platform.model.enums.BusinessResultCode;
import com.snake.operation.platform.model.enums.SysUserStatusEnum;
import com.snake.operation.platform.model.form.LoginForm;
import com.snake.operation.platform.model.form.RefreshTokenForm;
import com.snake.operation.platform.service.LoginService;
import com.snake.operation.platform.service.SysUserRoleService;
import com.snake.operation.platform.service.SysUserService;
import com.snake.operation.platform.utils.RefreshTokenUtils;
import io.github.yxsnake.pisces.web.core.utils.BizAssert;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final SysUserService sysUserService;

    private final SysUserRoleService sysUserRoleService;

    @Override
    public LoginDTO login(LoginForm form) {
        SysUser sysUser = sysUserService.lambdaQuery()
                .eq(SysUser::getUsername, form.getUsername())
                .list().stream().findFirst().orElse(null);
        BizAssert.isTrue("用户名或密码错误", Objects.isNull(sysUser));
        BizAssert.isTrue("账号已停用", SysUserStatusEnum.DISABLE.getValue().equals(sysUser.getStatus()));
        BizAssert.isTrue("密码错误",!sysUser.getPassword().equals(form.getPassword()));
        String userId = sysUser.getUserId();
        StpUtil.logout(userId);
        StpUtil.login(userId);
        String token = StpUtil.getTokenValue();
        LoginDTO loginDTO = LoginDTO.builder()
                .accessToken(token)
                .build();
        BeanUtils.copyProperties(sysUser,loginDTO);
        if(StrUtil.isBlank(sysUser.getAvatar())){
            loginDTO.setAvatar(SysUser.DEFAULT_AVATAR);
        }

        String refreshToken = RefreshTokenUtils.generateRefreshToken();

        RefreshTokenUtils.setRefreshToken(refreshToken,sysUser.getUserId());

        long tokenTimeout = StpUtil.getTokenTimeout() * 1000;
        long expiresNumber = DateUtil.date().getTime() + tokenTimeout;
        log.info("超时时间：{}",expiresNumber);
        loginDTO.setExpires(new Date(expiresNumber));
        loginDTO.setRefreshToken(refreshToken);
        // 查询当前用的角色标识
        Set<String> roleCodes =  sysUserRoleService.getCurrentUserRoles(userId);
        loginDTO.setRoles(roleCodes);
        return loginDTO;
    }

    @Override
    public RefreshTokenDTO refreshToken(RefreshTokenForm form) {
        RefreshTokenDTO refreshTokenDTO = RefreshTokenDTO.builder().build();
        String userId = RefreshTokenUtils.getUserId(form.getRefreshToken());
        BizAssert.isTrue(BusinessResultCode.INVALID_REFRESH_TOKEN, StrUtil.isBlank(userId));
        RefreshTokenUtils.removeRefreshToken(form.getRefreshToken());
        StpUtil.login(userId);
        String accessToken = StpUtil.getTokenValue();
        long tokenTimeout = StpUtil.getTokenTimeout() * 1000;
        long expiresNumber = DateUtil.date().getTime() + tokenTimeout;

        String refreshToken = RefreshTokenUtils.generateRefreshToken();
        RefreshTokenUtils.setRefreshToken(refreshToken,userId);

        refreshTokenDTO.setAccessToken(accessToken);
        refreshTokenDTO.setRefreshToken(refreshToken);
        refreshTokenDTO.setExpires(new Date(expiresNumber));
        return refreshTokenDTO;
    }

}
