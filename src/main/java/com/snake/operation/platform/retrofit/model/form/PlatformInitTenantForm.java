package com.snake.operation.platform.retrofit.model.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "初始化租户接受参数")
public class PlatformInitTenantForm {

    @Schema(description = "租户信息")
    private InitTenantInfoForm tenantInfoForm;

    @Schema(description = "角色信息")
    private List<InitTenantRoleInfoForm> roleFormList;

    @Schema(description = "菜单信息")
    private List<InitTenantMenuForm> menuFormList;

    @Schema(description = "接口信息")
    private List<InitTenantApiResourceForm> apiResourceFormList;

}
