package com.snake.operation.platform.model.form;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author: snake
 * @create-time: 2024-07-29
 * @description:
 * @version: 1.0
 */
@Data
@Schema(description = "启用或禁用平台用户请求参数")
public class SysUserEnableOrDisableForm {

    @Schema(description = "用户 ID")
    @NotBlank(message = "用户 ID 不能为空")
    private String userId;

    @Schema(description = "账号状态")
    @NotNull(message = "状态不能为空")
    private Integer status;
}
