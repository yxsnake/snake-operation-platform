package com.snake.operation.platform.model.fuzzy;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "运营平台部门模糊查询条件")
public class SysDeptFuzzyQueries {

    @Schema(description = "部门名称")
    private String deptName;
}
