package com.snake.operation.platform.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginDTO {

    private String token;

    private SysUserDTO userInfo;
}
