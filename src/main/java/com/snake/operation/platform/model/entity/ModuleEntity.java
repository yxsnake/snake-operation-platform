package com.snake.operation.platform.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import io.github.yxsnake.pisces.web.core.converter.Convert;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: snake
 * @create-time: 2024-07-16
 * @description: 模块
 * @version: 1.0
 */
@Data
@TableName(value = "p_module")
public class ModuleEntity implements Convert, Serializable {

    /**
     * 模块 ID
     */
    @TableId(type = IdType.NONE)
    private String moduleId;
    /**
     * 产品 ID
     */
    private String productId;
    /**
     * 模块名称
     */
    private String moduleName;
    /**
     * 是否免费 (1:是，0:付费)
     */
    private Integer isFree;

    private Integer deleted;
    /**
     * 创建时间
     */
    private Date createTime;
}
