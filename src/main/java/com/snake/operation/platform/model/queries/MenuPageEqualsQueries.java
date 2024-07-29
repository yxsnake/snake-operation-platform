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
@Schema(description = "菜单分页查询条件")
public class MenuPageEqualsQueries {

    @Schema(description = "菜单名称")
    private String menuName;
}
