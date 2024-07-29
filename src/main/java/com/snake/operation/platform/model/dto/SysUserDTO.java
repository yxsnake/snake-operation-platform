package com.snake.operation.platform.model.dto;

import io.github.yxsnake.pisces.web.core.converter.Convert;
import lombok.Data;

import java.util.List;

@Data
public class SysUserDTO implements Convert {

    private String userId;

    private String username;

    private String name;

    private Integer gender;

    private Integer status;

    private List<SysRoleDTO> roles;

}
