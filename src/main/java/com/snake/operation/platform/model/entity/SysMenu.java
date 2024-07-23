package com.snake.operation.platform.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.github.yxsnake.pisces.web.core.converter.Convert;
import lombok.Data;

/**
 * @author: snake
 * @create-time: 2024-07-23
 * @description:
 * @version: 1.0
 */
@Data
@TableName(value = "sys_menu")
public class SysMenu implements Convert {

    private String menuId;

    private String menuName;

    private Integer menuType;

    private String path;

    private String icon;

    private String componentName;

    private String parentId;

    private Integer rank;

    private Integer deleted;

}
