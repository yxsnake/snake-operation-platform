package com.snake.operation.platform.model.form;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @author: snake
 * @create-time: 2024-07-19
 * @description:
 * @version: 1.0
 */
@Data
@Schema(description = "同步菜单给租户请求参数")
public class SyncTenantMenuForm {

    @Schema(description = "平台菜单ID")
    private String platformMenuId;

    @Schema(description = "目标租户ID")
    private List<String> tenantIds;
}
