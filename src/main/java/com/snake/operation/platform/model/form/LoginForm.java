package com.snake.operation.platform.model.form;



import io.github.yxsnake.pisces.web.core.converter.Convert;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "登录提交表单参数")
public class LoginForm implements Convert {

    private String username;

    private String password;

}
