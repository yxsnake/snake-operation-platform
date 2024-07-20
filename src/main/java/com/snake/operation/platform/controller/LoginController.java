package com.snake.operation.platform.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.snake.operation.platform.model.dto.LoginDTO;
import com.snake.operation.platform.model.form.LoginForm;
import com.snake.operation.platform.service.LoginService;
import io.github.yxsnake.pisces.web.core.base.Result;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import io.github.yxsnake.pisces.web.core.framework.controller.BaseController;
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
@RequestMapping
@RequiredArgsConstructor
public class LoginController extends BaseController {

    private final LoginService loginService;

    @PostMapping(value = "/login")
    public ResponseEntity<Result<LoginDTO>> login(@RequestBody LoginForm form){
        LoginDTO login = loginService.login(form);
        return success(login);
    }

    @PostMapping(value = "/logout")
    public ResponseEntity<Result<Boolean>> logout(){
        StpUtil.logout(StpUtil.getLoginId());
        return success(Boolean.TRUE);
    }


}
