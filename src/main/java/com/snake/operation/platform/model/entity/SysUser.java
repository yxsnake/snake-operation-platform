package com.snake.operation.platform.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.github.yxsnake.pisces.web.core.converter.Convert;
import lombok.Data;

@Data
@TableName(value = "sys_user")
public class SysUser implements Convert {

    @TableId(type = IdType.NONE)
    private String userId;

    private String username;

    private String password;

    private String name;

    private Integer gender;

    private Integer status;

}
