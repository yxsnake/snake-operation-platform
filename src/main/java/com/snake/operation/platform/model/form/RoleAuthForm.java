package com.snake.operation.platform.model.form;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

/**
 * @author: snake
 * @create-time: 2024-07-29
 * @description:
 * @version: 1.0
 */
@Data
@Schema(name = "角色授权菜单资源")
public class RoleAuthForm {

    @NotBlank(message = "角色ID不能为空")
    private String roleId;

    @NotEmpty(message = "授权菜单不能为空")
    private List<String> menuIds;
}
