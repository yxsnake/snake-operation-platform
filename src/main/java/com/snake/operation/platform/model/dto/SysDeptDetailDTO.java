package com.snake.operation.platform.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
@Schema(name = "部门树对象")
public class SysDeptDetailDTO {

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

    @Schema(description = "部门状态(0-正常，1-禁用)")
    private Integer status;

    @Schema(description = "排序")
    private Long rank;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "创建时间")
    private Date createTime;

}
