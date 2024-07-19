package com.snake.operation.platform.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author: snake
 * @create-time: 2024-07-19
 * @description:
 * @version: 1.0
 */
@Data
@Schema(name = "菜单传输对象")
public class MenuDTO {

    @Schema(name = "平台资源ID")
    private String platformResourceId;

    @Schema(name = "上级资源ID")
    private String parentId;

    @Schema(name = "资源类型(0:目录,1:菜单, 2:按钮 ,3:外链,4:接口)")
    private Integer resourceType;

    @Schema(name = "资源名称")
    private String name;

    @Schema(name = "路由路径(浏览器地址栏路径)")
    private String path;

    @Schema(name = "组件路径(vue页面完整路径，省略.vue后缀)")
    private String component;

    @Schema(name = "按钮权限标识")
    private String perm;

    @Schema(name = "跳转路径")
    private String redirect;

    @Schema(name = "菜单图标")
    private String icon;

    @Schema(name = "备注")
    private String remark;

    @Schema(name = "排序")
    private Long sort;

    @Schema(name = "是否禁用（0-否，1-是）")
    private Integer disabled;

    @Schema(name = "是否删除（0-否，1-是）")
    private Integer deleted;

    @Schema(name = "层级（0～5， 最多支持 5 层）")
    private Integer level;

    @Schema(name = "所属模块ID")
    private String moduleId;

}
