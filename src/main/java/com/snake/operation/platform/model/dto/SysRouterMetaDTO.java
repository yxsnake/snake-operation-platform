package com.snake.operation.platform.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Collection;

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

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer rank;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Collection<String> roles;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Collection<String> auths;

}
