package com.snake.operation.platform.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author: snake
 * @create-time: 2024-07-16
 * @description:
 * @version: 1.0
 */
@Data
@TableName(value = "p_menu")
public class MenuEntity {

    public final static String ROOT = "0";

    public final static Integer ROOT_LEVEL = 1;
    /**
     * 平台资源ID
     */
    @TableId(value = "p_resource_id", type = IdType.AUTO)
    private String platformResourceId;

    /**
     * 上级资源 ID
     */
    private String parentId;

    /**
     * 资源类型(0:目录,1:菜单, 2:按钮 ,3:外链,4:接口)
     */
    private Integer resourceType;

    /**
     * 资源名称
     */
    private String name;

    /**
     * 路由路径(浏览器地址栏路径)
     */
    private String path;

    /**
     * 组件路径(vue页面完整路径，省略.vue后缀)
     */
    private String component;

    /**
     * 按钮权限标识
     */
    private String perm;

    /**
     * 跳转路径
     */
    private String redirect;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 备注
     */
    private String remark;

    /**
     * 排序
     */
    private Long sort;

    /**
     * 是否禁用（0-否，1-是）
     */
    private Integer disabled;

    /**
     * 是否删除（0-否，1-是）
     */
    private Integer deleted;

    /**
     * 层级（0～5， 最多支持 5 层）
     */
    private Integer level;

    private String moduleId;

}
