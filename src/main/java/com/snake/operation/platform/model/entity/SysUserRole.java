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
@TableName(value = "sys_user_role")
public class SysUserRole implements Convert {

    @TableId(type = IdType.NONE)
    private String id;

    private String userId;

    private String roleId;


}
