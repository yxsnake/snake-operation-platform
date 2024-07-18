package com.snake.operation.platform.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author: snake
 * @create-time: 2024-07-16
 * @description:
 * @version: 1.0
 */
@Data
@TableName(value = "p_api_resource")
public class ApiResourceEntity {

    public final static String ROOT = "0";

    public final static Integer LEVEL = 1;
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
     * 资源名称
     */
    private String name;

    /**
     * 路由路径(浏览器地址栏路径)
     */
    private String path;
    /**
     * 按钮权限标识
     */
    private String perm;

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
     * 下发状态(未下发-0，已下发-1)
     */
    private Integer issuedStatus;

    private Date createTime;

    private Date updateTime;

}
