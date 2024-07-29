package com.snake.operation.platform.model.form;

import io.github.yxsnake.pisces.web.core.converter.Convert;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author: snake
 * @create-time: 2024-07-29
 * @description:
 * @version: 1.0
 */
@Data
@Schema(name = "系统角色创建编辑参数对象")
public class SysRoleForm implements Convert {

    @Schema(name = "角色ID")
    private String roleId;

    @Schema(name = "角色编码")
    private String roleCode;

    @Schema(name = "角色名称")
    private String roleName;

    @Schema(name = "备注")
    private String remark;

    public interface Create{}

    public interface Modify{}
}
