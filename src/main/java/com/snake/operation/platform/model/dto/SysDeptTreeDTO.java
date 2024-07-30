package com.snake.operation.platform.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "部门树对象")
public class SysDeptTreeDTO {

    @Schema(description = "部门ID")
    @JsonProperty("id")
    private String deptId;

    @Schema(description = "上级部门ID")
    private String parentId;

    @Schema(description = "部门名称")
    @JsonProperty("name")
    private String deptName;

    @Schema(description = "部门负责人")
    private String personInCharge;

    @Schema(description = "负责人电话")
    private String phone;

    @Schema(description = "负责人邮箱")
    private String email;

    private Integer status;

}
