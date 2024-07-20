package com.snake.operation.platform.model.form;

import io.github.yxsnake.pisces.web.core.converter.Convert;
import lombok.Data;

@Data
public class LoginForm implements Convert {

    private String username;

    private String password;

}
