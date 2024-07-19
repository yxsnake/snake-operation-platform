package com.snake.operation.platform.model.form;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import io.github.yxsnake.pisces.web.core.converter.Convert;

import java.io.Serializable;
import java.util.List;

/**
 * @author: snake
 * @create-time: 2024-07-16
 * @description: 租户初始化表单参数
 * @version: 1.0
 */
@Data
@Schema(name = "租户创建")
public class TenantForm implements Convert, Serializable {

    @NotBlank(message = "租户唯一标识不能为空",groups = {Modify.class})
    private String tenantId;

    @Schema(name = "租户名称")
    @NotBlank(message = "租户名称不能为空",groups = {Create.class,Modify.class})
    private String tenantName;

    @Schema(name = "租户简称")
    @NotBlank(message = "租户简称不能为空",groups = {Create.class,Modify.class})
    private String introduction;

    @Schema(name = "租户联系人")
    @NotBlank(message = "租户联系人不能为空",groups = {Create.class,Modify.class})
    private String supperPerson;

    @Schema(name = "租户联系人邮箱")
    @NotBlank(message = "租户联系人邮箱不能为空",groups = {Create.class,Modify.class})
    private String email;

    @Schema(name = "租户联系人电话")
    @NotBlank(message = "租户联系人电话不能为空",groups = {Create.class,Modify.class})
    private String phone;

    @Schema(name = "购买的模块")
    @NotEmpty(message = "购买的模块不能为空",groups = {Create.class,Modify.class})
    private List<String> moduleIds;

    public interface Create{

    }
    public interface Modify{

    }
}

