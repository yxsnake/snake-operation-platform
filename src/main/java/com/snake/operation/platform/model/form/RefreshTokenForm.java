package com.snake.operation.platform.model.form;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author: snake
 * @create-time: 2024-07-23
 * @description:
 * @version: 1.0
 */
@Data
@Schema(name = "刷新token请求参数")
public class RefreshTokenForm {

    private String refreshToken;
}
