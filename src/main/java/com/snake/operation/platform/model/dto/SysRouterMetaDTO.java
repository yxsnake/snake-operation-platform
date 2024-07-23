package com.snake.operation.platform.model.dto;

import lombok.Data;

import java.util.List;

/**
 * @author: snake
 * @create-time: 2024-07-23
 * @description:
 * @version: 1.0
 */
@Data
public class SysRouterMetaDTO {

    private String title;

    private String icon;

    private Integer rank;

    private List<String> roles;

    private List<String> auths;

}
