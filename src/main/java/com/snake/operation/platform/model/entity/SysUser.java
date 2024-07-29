package com.snake.operation.platform.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.github.yxsnake.pisces.web.core.converter.Convert;
import lombok.Data;

@Data
@TableName(value = "sys_user")
public class SysUser implements Convert {

    public final static String DEFAULT_AVATAR = "https://nimg.ws.126.net/?url=http%3A%2F%2Fdingyue.ws.126.net%2F2024%2F0511%2Fb0cd6623j00sdas3j0017d000gx00g1g.jpg&thumbnail=660x2147483647&quality=80&type=jpg";

    @TableId(type = IdType.NONE)
    private String userId;

    private String username;

    private String password;

    private String name;

    private Integer gender;

    private Integer status;

    private String avatar;

}
