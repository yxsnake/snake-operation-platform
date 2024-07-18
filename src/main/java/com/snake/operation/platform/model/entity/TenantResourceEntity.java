package com.snake.operation.platform.model.entity;

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
@TableName("tenant_resource")
public class TenantResourceEntity {

    private String id;

    private String tenantId;

    private String productId;

    private String moduleId;

    private String menuId;

}
