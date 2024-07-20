package com.snake.operation.platform.service;

import com.snake.operation.platform.model.dto.LoginDTO;
import com.snake.operation.platform.model.form.LoginForm;

public interface LoginService {


    LoginDTO login(LoginForm form);
}
