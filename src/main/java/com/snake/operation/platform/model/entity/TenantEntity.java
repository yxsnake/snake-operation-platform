package com.snake.operation.platform.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author: snake
 * @create-time: 2024-07-16
 * @description: 租户
 * @version: 1.0
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tenant")
public class TenantEntity {

    @TableId(type = IdType.NONE)
    private String tenantId;

    private String tenantName;

    private String introduction;

    private String supperPerson;

    private String email;

    private String phone;

    private String supperAccount;

    private String supperPassword;

    private Date createTime;

    private Date updateTime;


}
