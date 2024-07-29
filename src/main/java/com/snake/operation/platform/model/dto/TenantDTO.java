package com.snake.operation.platform.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * @author: snake
 * @create-time: 2024-07-19
 * @description:
 * @version: 1.0
 */
@Data
@Schema(name = "租户信息传输对象")
public class TenantDTO {

    @Schema(description = "租户唯一标识")
    private String tenantId;

    @Schema(description = "租户名称")
    private String tenantName;

    @Schema(description = "租户简称")
    private String introduction;

    @Schema(description = "租户联系人")
    private String supperPerson;

    @Schema(description = "联系邮箱")
    private String email;

    @Schema(description = "联系电话")
    private String phone;

    @Schema(description = "租户超级管理员账号")
    private String supperAccount;

    @Schema(description = "审核结论 (0-待审核同步，1-通过 , 2-驳回)")
    private Integer auditStatus;

    @Schema(description = "审核意见")
    private String auditRemark;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "修改时间")
    private Date updateTime;
}
