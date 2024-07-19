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
public class ModuleDTO {

    @Schema(name = "模块唯一标识")
    private String moduleId;

    @Schema(name = "产品唯一标识")
    private String productId;

    @Schema(name = "模块名称")
    private String moduleName;

    @Schema(name = "是否免费 (1:是，0:付费)")
    private Integer isFree;

}
