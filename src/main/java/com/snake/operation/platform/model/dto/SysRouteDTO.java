package com.snake.operation.platform.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @author: snake
 * @create-time: 2024-07-23
 * @description:
 * @version: 1.0
 */

@Data
@Schema(name = "菜单路由传说对象")
public class SysRouteDTO {

    @JsonIgnore
    private String id;

    @Schema(description = "路由路径")
    private String path;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;

    @JsonIgnore
    private String parentId;

    @Schema(description = "路由meta信息")
    private SysRouterMetaDTO meta;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Schema(description = "路由子级信息列表")
    private List<SysRouteDTO> children;

}
