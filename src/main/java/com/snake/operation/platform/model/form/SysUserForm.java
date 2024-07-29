package com.snake.operation.platform.model.form;

import io.github.yxsnake.pisces.web.core.converter.Convert;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

/**
 * @author: snake
 * @create-time: 2024-07-29
 * @description:
 * @version: 1.0
 */
@Data
@Schema(description = "系统用户创建编辑参数对象")
public class SysUserForm implements Convert {

    @Schema(description = "用户ID")
    @NotBlank(message = "用户ID不能为空",groups = {Modify.class})
    private String userId;

    @Schema(description = "账号")
    @NotBlank(message = "账号不能为空",groups = {Create.class})
    private String username;

    @Schema(description = "真实姓名")
    @NotBlank(message = "真实姓名不能为空",groups = {Create.class, Modify.class})
    private String name;

    @Schema(description = "性别")
    private Integer gender;

    @Schema(description = "图像")
    private String avatar;

    @Schema(description = "角色ID")
    private List<String> roleIds;

    public interface Create{}

    public interface Modify{}
}
