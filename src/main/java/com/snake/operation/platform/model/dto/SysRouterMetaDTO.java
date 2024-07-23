package com.snake.operation.platform.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Collection;
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

    @JsonIgnore
    private Collection<String> roles;

    @JsonIgnore
    private Collection<String> auths;

}
