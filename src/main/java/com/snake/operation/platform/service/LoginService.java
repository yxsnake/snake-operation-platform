package com.snake.operation.platform.service;

import com.snake.operation.platform.model.dto.LoginDTO;
import com.snake.operation.platform.model.dto.RefreshTokenDTO;
import com.snake.operation.platform.model.form.LoginForm;
import com.snake.operation.platform.model.form.RefreshTokenForm;

public interface LoginService {


    LoginDTO login(LoginForm form);

    RefreshTokenDTO refreshToken(RefreshTokenForm form);
}
