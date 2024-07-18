package com.snake.operation.platform.model.form;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import io.github.yxsnake.pisces.web.core.converter.Convert;

import java.io.Serializable;

/**
 * @author: snake
 * @create-time: 2024-07-16
 * @description:
 * @version: 1.0
 */
@Data
@Schema(name = "菜单表单对象")
public class MenuForm implements Convert, Serializable {

    @Schema(name = "平台资源ID")
    @NotBlank(message = "平台资源ID不能为空",groups = {Modify.class})
    private String platformResourceId;

    @Schema(name = "上级ID")
    private String parentId;

    @Schema(name = "资源类型")
    @NotBlank(message = "资源类型不能为空",groups = {Create.class,Modify.class})
    private Integer resourceType;

    @Schema(name = "资源名称")
    @NotBlank(message = "资源名称不能为空",groups = {Create.class,Modify.class})
    private String name;

    @Schema(name = "路由地址")
    private String path;

    @Schema(name = "组件名称")
    private String component;

    @Schema(name = "权限标识")
    private String perm;

    @Schema(name = "跳转地址")
    private String redirect;

    @Schema(name = "图标")
    @NotBlank(message = "图标不能为空")
    private String icon;

    @Schema(name = "备注")
    private String remark;

    @Schema(name = "排序")
    private Long sort;

    @Schema(name = "所属模块")
    @NotBlank(message = "所属模块不能为空")
    private String moduleId;

    public interface Create{}

    public interface Modify{}
}
