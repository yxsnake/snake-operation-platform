package com.snake.operation.platform.model.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author: snake
 * @create-time: 2024-07-19
 * @description:
 * @version: 1.0
 */
@Data
public class TenantDTO {

    private String tenantId;

    private String tenantName;

    private String introduction;

    private String supperPerson;

    private String email;

    private String phone;

    private String supperAccount;

    private Integer auditStatus;

    private String auditRemark;

    private Date createTime;

    private Date updateTime;
}
