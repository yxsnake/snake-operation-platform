package com.snake.operation.platform.model.fuzzy;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author: snake
 * @create-time: 2024-07-29
 * @description:
 * @version: 1.0
 */
@Data
@Schema(name = "运营平台角色分页模糊查询条件")
public class SysRoleFuzzyQueries {

    private String roleName;
}
