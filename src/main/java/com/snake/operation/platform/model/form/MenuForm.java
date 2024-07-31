package com.snake.operation.platform.model.form;



import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
@Schema(description = "菜单表单对象")
public class MenuForm implements Convert, Serializable {

    @Schema(description = "平台资源ID")
    @NotBlank(message = "平台资源ID不能为空",groups = {Modify.class})
    private String platformResourceId;

    @Schema(description = "上级ID")
    private String parentId;

    @Schema(description = "资源类型")
    @NotNull(message = "资源类型不能为空",groups = {Create.class,Modify.class})
    private Integer resourceType;

    @Schema(description = "资源名称")
    @NotBlank(message = "资源名称不能为空",groups = {Create.class,Modify.class})
    private String name;

    @Schema(description = "路由地址")
    private String path;

    @Schema(description = "组件名称")
    private String component;

    @Schema(description = "权限标识")
    private String perm;

    @Schema(description = "跳转地址")
    private String redirect;

    @Schema(description = "图标")
    @NotBlank(message = "图标不能为空")
    private String icon;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "排序")
    private Long sort;

    @Schema(description = "所属模块")
    @NotBlank(message = "所属模块不能为空")
    private String moduleId;

    public interface Create{}

    public interface Modify{}
}
