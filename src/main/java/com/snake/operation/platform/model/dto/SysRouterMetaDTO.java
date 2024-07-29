package com.snake.operation.platform.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Collection;

/**
 * @author: snake
 * @create-time: 2024-07-23
 * @description:
 * @version: 1.0
 */
@Data
@Schema(name = "路由meta信息对象")
public class SysRouterMetaDTO {

    @Schema(description = "菜单名称")
    private String title;

    @Schema(description = "菜单图标")
    private String icon;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Schema(description = "排序")
    private Integer rank;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Schema(description = "当前菜单对于的角色标识集合")
    private Collection<String> roles;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Schema(description = "按钮对应的权限标识集合")
    private Collection<String> auths;

}
