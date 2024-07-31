package com.snake.operation.platform.model.form;


import io.github.yxsnake.pisces.web.core.converter.Convert;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(name = "部门创建或修改表单提交数据对象")
public class SysDeptForm implements Convert, Serializable {

    @Schema(description = "部门ID")
    @NotBlank(message = "部门ID不能为空",groups = {Modify.class})
    private String deptId;

    @Schema(description = "上级部门ID")
    private String parentId;

    @Schema(description = "部门名称")
    @NotBlank(message = "部门名称不能为空",groups = {Create.class,Modify.class})
    private String deptName;

    @Schema(description = "部门负责人")
    private String personInCharge;

    @Schema(description = "负责人电话")
    private String phone;

    @Schema(description = "负责人邮箱")
    private String email;

    @Schema(description = "排序")
    private Long rank;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "状态")
    private Integer status;

    public interface Create{}

    public interface Modify{}
}
