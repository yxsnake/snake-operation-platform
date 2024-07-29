package com.snake.operation.platform.model.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author: snake
 * @create-time: 2024-07-16
 * @description:
 * @version: 1.0
 */
@Data
@Schema(description = "租户审核提交对象")
public class TenantAuditForm {

    @Schema(description = "租户唯一标识 ")
    private String tenantId;

    @Schema(description = "审核结论 (0-待审核同步，1-通过 , 2-驳回)")
    private Integer auditStatus;

    @Schema(description = "审核意见")
    private String auditRemark;
}
