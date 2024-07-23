package com.snake.operation.platform.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.github.yxsnake.pisces.web.core.converter.Convert;
import lombok.Data;

@Data
@TableName(value = "sys_user")
public class SysUser implements Convert {

    public final static String DEFAULT_AVATAR = "https://i1.hdslb.com/bfs/face/98a570a6c6d6a263bcb0cba9e15e492125e9d310.jpg@120w_120h_1c";

    @TableId(type = IdType.NONE)
    private String userId;

    private String username;

    private String password;

    private String name;

    private Integer gender;

    private Integer status;

}
