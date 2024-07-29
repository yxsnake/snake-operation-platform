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
@Schema(name = "启用或禁用平台用户请求参数")
public class SysRoleEnableOrDisableForm {

    @Schema(name = "角色ID")
    @NotBlank(message = "角色ID不能为空")
    private String roleId;

    @Schema(name = "角色状态")
    @NotNull(message = "状态不能为空")
    private Integer status;
}
