package com.snake.operation.platform.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author: snake
 * @create-time: 2024-07-16
 * @description: 租户拥有的产品模块菜单关联关系
 * @version: 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("p_tenant_resource")
public class TenantResourceEntity {

    @TableId(type = IdType.NONE)
    private String id;

    private String tenantId;

    private String productId;

    private String moduleId;

    private String menuId;

}
