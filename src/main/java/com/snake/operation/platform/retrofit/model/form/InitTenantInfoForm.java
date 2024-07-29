package com.snake.operation.platform.retrofit.model.form;

import io.github.yxsnake.pisces.web.core.converter.Convert;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "初始化相关租户信息")
public class InitTenantInfoForm implements Convert {

    @Schema(description = "租户ID")
    private String tenantId;

    @Schema(description = "租户名称")
    private String tenantName;

    @Schema(description = "租户简称")
    private String introduction;

    @Schema(description = "租户联系人")
    private String supperPerson;

    @Schema(description = "联系邮箱")
    private String email;

    @Schema(description = "联系电话")
    private String phone;

    @Schema(description = "租户超级管理员账号")
    private String supperAccount;

    @Schema(description = "租户超级管理员密码")
    private String supperPassword;

}
