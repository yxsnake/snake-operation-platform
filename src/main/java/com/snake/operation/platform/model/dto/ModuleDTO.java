package com.snake.operation.platform.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author: snake
 * @create-time: 2024-07-16
 * @description:
 * @version: 1.0
 */
@Data
@Schema(name = "模块信息传输对象")
public class ModuleDTO {

    @Schema(description = "模块唯一标识")
    private String moduleId;

    @Schema(description = "产品唯一标识")
    private String productId;

    @Schema(description = "模块名称")
    private String moduleName;

    @Schema(description = "是否免费 (1:是，0:付费)")
    private Integer isFree;

}
