package com.snake.operation.platform.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.github.yxsnake.pisces.web.core.converter.Convert;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value = "sys_dept")
public class SysDept implements Convert {

    public final static String ROOT = "0";

    @TableId(type = IdType.NONE)
    private String deptId;

    private String parentId;

    private String deptName;

    private String personInCharge;

    private String phone;

    private String email;

    private Integer status;

    private Long rank;

    private String remark;

    private Date createTime;

    private Date updateTime;
}
