package com.snake.operation.platform.model.queries;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "部门分页查询参数")
public class SysDeptEqualsQueries {

    @Schema(description = "部门状态")
    private Integer status;
}
