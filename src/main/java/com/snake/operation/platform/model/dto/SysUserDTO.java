package com.snake.operation.platform.model.dto;

import io.github.yxsnake.pisces.web.core.converter.Convert;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(name = "用户信息传输对象")
public class SysUserDTO implements Convert {

    @Schema(description = "用户唯一标识")
    private String userId;

    @Schema(description = "登录账号")
    private String username;

    @Schema(description = "姓名")
    private String name;

    @Schema(description = "性别(0-女,1-男)")
    private Integer gender;

    @Schema(description = "账号状态(0-正常,1-禁用)")
    private Integer status;

    @Schema(description = "用户拥有的角色列表")
    private List<SysRoleDTO> roles;

}
