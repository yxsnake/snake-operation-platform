package com.snake.operation.platform.model.queries;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author: snake
 * @create-time: 2024-07-19
 * @description:
 * @version: 1.0
 */
@Data
@Schema(name = "产品分页查询参数")
public class TenantPageEqualQueries {

    @Schema(description = "租户名称")
    private String tenantName;
}
