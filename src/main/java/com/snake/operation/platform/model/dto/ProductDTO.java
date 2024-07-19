package com.snake.operation.platform.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * @author: snake
 * @create-time: 2024-07-16
 * @description:
 * @version: 1.0
 */
@Data
@Schema(name = "产品信息传输对象")
public class ProductDTO {

    @Schema(name = "产品唯一标识")
    private String productId;

    @Schema(name = "产品名称")
    private String productName;

    @Schema(name = "是否免费 (1:付费，0:免费)")
    private Integer isFree;

    @Schema(name = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}
