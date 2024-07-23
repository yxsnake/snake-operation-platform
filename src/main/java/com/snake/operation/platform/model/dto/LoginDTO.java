package com.snake.operation.platform.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class LoginDTO {

    private String accessToken;

    private Date expires;

    private String userId;

    private String username;

    private String name;

    private Integer gender;

    private Integer status;
}
