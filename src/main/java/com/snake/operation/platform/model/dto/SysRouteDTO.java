package com.snake.operation.platform.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    private String id;

    private String path;

    @JsonIgnore
    private String name;


    private String parentId;

    private SysRouterMetaDTO meta;

    @JsonIgnore
    private List<SysRouteDTO> children;

}
