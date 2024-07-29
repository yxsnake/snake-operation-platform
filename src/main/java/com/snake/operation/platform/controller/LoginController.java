package com.snake.operation.platform.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.snake.operation.platform.model.dto.LoginDTO;
import com.snake.operation.platform.model.dto.RefreshTokenDTO;
import com.snake.operation.platform.model.entity.SysUser;
import com.snake.operation.platform.model.form.LoginForm;
import com.snake.operation.platform.model.form.RefreshTokenForm;
import com.snake.operation.platform.service.LoginService;
import io.github.yxsnake.pisces.web.core.base.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import io.github.yxsnake.pisces.web.core.framework.controller.BaseController;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: snake
 * @create-time: 2024-07-16
 * @description: 登录
 * @version: 1.0
 */
@Tag(name = "运营平台登录")
@Slf4j
@RestController
@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
public class LoginController extends BaseController {

    private final LoginService loginService;

    @Operation(summary = "登录")
    @PostMapping(value = "/login")
    public ResponseEntity<Result<LoginDTO>> login(@RequestBody LoginForm form){
        LoginDTO login = loginService.login(form);
        return success(login);
    }

    @Operation(summary = "登出")
    @PostMapping(value = "/logout")
    public ResponseEntity<Result<Boolean>> logout(){
        StpUtil.logout(StpUtil.getLoginId());
        return success(Boolean.TRUE);
    }


    @Operation(summary = "刷新token")
    @PostMapping(value = "/refresh-token")
    public ResponseEntity<Result<RefreshTokenDTO>> refreshToken(@RequestBody RefreshTokenForm form){
        return success(loginService.refreshToken(form));
    }


}
