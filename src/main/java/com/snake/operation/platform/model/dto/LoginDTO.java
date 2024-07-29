package com.snake.operation.platform.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.Set;


@Data
@Builder
@Schema(name = "登录响应结果")
public class LoginDTO {

    @Schema(description = "访问令牌")
    private String accessToken;

    @Schema(description = "刷新令牌")
    private String refreshToken;

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss",timezone = "GMT+8")
    @Schema(description = "访问令牌过期时间")
    private Date expires;

    @Schema(description = "当前用户ID")
    private String userId;

    @Schema(description = "用户登录账号")
    private String username;

    @Schema(description = "姓名")
    private String name;

    @Schema(description = "性别")
    private Integer gender;

    @Schema(description = "账号状态")
    private Integer status;

    @Schema(description = "头像")
    private String avatar;

    @Schema(description = "拥有的角色")
    private Set<String> roles;
}
