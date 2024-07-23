package com.snake.operation.platform.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.github.yxsnake.pisces.web.core.converter.Convert;
import lombok.Data;

/**
 * @author: snake
 * @create-time: 2024-07-23
 * @description:
 * @version: 1.0
 */
@Data
@TableName(value = "sys_role")
public class SysRole implements Convert {

    private String roleId;

    private String roleCode;

    private String roleName;

    private Integer deleted;

}
