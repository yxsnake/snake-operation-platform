package com.snake.operation.platform.model.queries;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author: snake
 * @create-time: 2024-07-29
 * @description:
 * @version: 1.0
 */
@Data
@Schema(name = "运营平台用户分页查询条件")
public class SysUserPageEqualsQueries {

    @Schema(description = "用户状态")
    private Integer status;
}
