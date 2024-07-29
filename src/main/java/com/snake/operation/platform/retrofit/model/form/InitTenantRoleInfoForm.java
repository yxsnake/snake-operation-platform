package com.snake.operation.platform.retrofit.model.form;

import io.github.yxsnake.pisces.web.core.converter.Convert;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "初始化相关角色信息")
public class InitTenantRoleInfoForm implements Convert {

    @Schema(description = "平台角色ID")
    private String platformRoleId;

    @Schema(description = "角色名称")
    private String roleName;

    @Schema(description = "角色编码")
    private String roleCode;

    @Schema(description = "角色备注")
    private String remark;

}
