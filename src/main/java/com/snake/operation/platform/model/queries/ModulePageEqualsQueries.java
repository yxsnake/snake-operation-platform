package com.snake.operation.platform.model.queries;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author: snake
 * @create-time: 2024-07-16
 * @description:
 * @version: 1.0
 */
@Data
@Schema(name = "模块分页查询参数")
public class ModulePageEqualsQueries {

    private String moduleName;

}
