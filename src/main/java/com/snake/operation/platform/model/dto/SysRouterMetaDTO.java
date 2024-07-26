package com.snake.operation.platform.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Sets;
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

    private Integer rank;

    private Boolean showLink = Boolean.FALSE;

    private Collection<String> roles = Sets.newHashSet();

    private Collection<String> auths = Sets.newHashSet();

}
