package com.snake.operation.platform.retrofit.model.form;

import io.github.yxsnake.pisces.web.core.converter.Convert;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "初始化相关接口权限信息")
public class InitTenantApiResourceForm implements Convert {

    @Schema(description = "资源ID")
    @NotBlank(message = "资源ID不能为空")
    private String platformResourceId;

    @Schema(description = "资源名称")
    @NotBlank(message = "资源名称不能为空")
    private String name;

    @Schema(description = "接口地址")
    @NotBlank(message = "接口地址不能为空")
    private String path;

    @Schema(description = "权限标识")
    @NotBlank(message = "权限标识不能为空")
    private String perm;

    @Schema(description = "备注")
    private String remark;

}
