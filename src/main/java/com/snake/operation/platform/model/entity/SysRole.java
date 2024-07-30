package com.snake.operation.platform.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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

    @TableId(type = IdType.NONE)
    private String roleId;

    private String roleCode;

    private String roleName;

    private String remark;

    private Integer deleted;

}
