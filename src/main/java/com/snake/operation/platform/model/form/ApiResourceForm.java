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
@Schema(description = "接口表单对象")
public class ApiResourceForm implements Convert, Serializable {

    @Schema(description = "上级资源ID")
    @NotBlank(message = "上级资源ID不能为空",groups = {Create.class,Modify.class})
    private String parentId;

    @Schema(description = "资源ID")
    @NotBlank(message = "资源ID不能为空",groups = {Modify.class})
    private String platformResourceId;

    @Schema(description = "资源名称")
    @NotBlank(message = "资源名称不能为空",groups = {Create.class,Modify.class})
    private String name;

    @Schema(description = "接口地址")
    @NotBlank(message = "接口地址不能为空",groups = {Create.class,Modify.class})
    private String path;

    @Schema(description = "权限标识")
    @NotBlank(message = "权限标识不能为空",groups = {Create.class,Modify.class})
    private String perm;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "排序")
    private Long sort;

    public interface Create{}

    public interface Modify{}
}
