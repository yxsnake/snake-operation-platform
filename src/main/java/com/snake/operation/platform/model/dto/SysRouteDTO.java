package com.snake.operation.platform.model.dto;

import lombok.Data;

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

    private String name;

    private SysRouterMetaDTO meta;

}
