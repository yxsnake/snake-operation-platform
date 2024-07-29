package com.snake.operation.platform.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: snake
 * @create-time: 2024-07-29
 * @description:
 * @version: 1.0
 */
@Data
@Schema(name = "系统角色信息传输对象")
public class SysRoleDTO implements Serializable {

    private String roleId;

    private String roleCode;

    private String roleName;

    private String remark;

    private Integer deleted;
}
