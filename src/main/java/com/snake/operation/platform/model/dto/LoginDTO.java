package com.snake.operation.platform.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.Set;


@Data
@Builder
public class LoginDTO {

    private String accessToken;

    private String refreshToken;

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss",timezone = "GMT+8")
    private Date expires;

    private String userId;

    private String username;

    private String name;

    private Integer gender;

    private Integer status;

    private String avatar;

    private Set<String> roles;
}
