package com.snake.operation.platform.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * @author: snake
 * @create-time: 2024-07-23
 * @description:
 * @version: 1.0
 */

@Data
public class SysRouteDTO {

    @JsonIgnore
    private String id;

    private String path;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;

    @JsonIgnore
    private String parentId;

    private SysRouterMetaDTO meta;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<SysRouteDTO> children;

}
