package com.snake.operation.platform.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.github.yxsnake.pisces.web.core.converter.Convert;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: snake
 * @create-time: 2024-07-16
 * @description: 产品
 * @version: 1.0
 */
@Data
@TableName(value = "p_product")
public class ProductEntity implements Convert, Serializable {

    /**
     * 产品 ID
     */
    @TableId(type = IdType.NONE)
    private String productId;
    /**
     * 产品名称
     */
    private String productName;
    /**
     * 是否免费 (1:付费，0:免费)
     */
    private Integer isFree;
    /**
     * 删除标识
     */
    private Integer deleted;

    /**
     * 创建时间
     */
    private Date createTime;




}
