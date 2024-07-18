/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1-123456
 Source Server Type    : MySQL
 Source Server Version : 50744
 Source Host           : localhost:3306
 Source Schema         : snake_operation_platform

 Target Server Type    : MySQL
 Target Server Version : 50744
 File Encoding         : 65001

 Date: 16/07/2024 17:30:23
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for module
-- ----------------------------
DROP TABLE IF EXISTS `module`;
CREATE TABLE `module` (
  `module_id` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '模块 ID',
  `product_id` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '模块 ID',
  `module_name` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '模块名称',
  `is_free` smallint(3) DEFAULT NULL COMMENT '是否免费 (1:是，0:付费)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`module_id`),
  UNIQUE KEY `uq_module_name` (`module_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='模块';

-- ----------------------------
-- Records of module
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for p_api_resource
-- ----------------------------
DROP TABLE IF EXISTS `p_api_resource`;
CREATE TABLE `p_api_resource` (
  `p_resource_id` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '平台资源ID',
  `parent_id` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '上级资源 ID',
  `name` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '资源名称',
  `path` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '路由路径(浏览器地址栏路径)',
  `perm` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '按钮权限标识',
  `remark` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注',
  `sort` int(10) DEFAULT NULL COMMENT '排序',
  `disabled` smallint(3) DEFAULT NULL COMMENT '是否禁用（0-否，1-是）',
  `deleted` smallint(3) DEFAULT NULL COMMENT '是否删除（0-否，1-是）',
  `issued_status` smallint(3) DEFAULT '0' COMMENT '下发状态(0-未下发，1-已下发',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_id` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '创建人ID',
  `create_user_name` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '创建人名称',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_user_id` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '修改人ID',
  `update_user_name` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '修改人名称',
  PRIMARY KEY (`p_resource_id`),
  UNIQUE KEY `uq_perm` (`perm`) USING BTREE,
  UNIQUE KEY `uq_path` (`path`) USING BTREE,
  UNIQUE KEY `uq_name` (`name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='平台预设API资源';

-- ----------------------------
-- Records of p_api_resource
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for p_menu
-- ----------------------------
DROP TABLE IF EXISTS `p_menu`;
CREATE TABLE `p_menu` (
  `p_resource_id` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '平台资源ID',
  `parent_id` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '上级资源 ID',
  `resource_type` smallint(3) DEFAULT NULL COMMENT '资源类型(0:目录,1:菜单, 2:按钮 ,3:外链,4:接口)',
  `name` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '资源名称',
  `path` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '路由路径(浏览器地址栏路径)',
  `component` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '组件路径(vue页面完整路径，省略.vue后缀)',
  `perm` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '按钮权限标识',
  `redirect` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '跳转路径',
  `icon` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '菜单图标',
  `remark` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注',
  `sort` int(10) DEFAULT NULL COMMENT '排序',
  `disabled` smallint(3) DEFAULT NULL COMMENT '是否禁用（0-否，1-是）',
  `deleted` smallint(3) DEFAULT NULL COMMENT '是否删除（0-否，1-是）',
  `level` smallint(3) DEFAULT NULL COMMENT '层级（0～5， 最多支持 5 层）',
  `module_id` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '模块 ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_id` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '创建人ID',
  `create_user_name` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '创建人名称',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_user_id` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '修改人ID',
  `update_user_name` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '修改人名称',
  PRIMARY KEY (`p_resource_id`),
  UNIQUE KEY `uq_perm` (`perm`) USING BTREE,
  UNIQUE KEY `uq_component` (`component`) USING BTREE,
  UNIQUE KEY `uq_path` (`path`) USING BTREE,
  UNIQUE KEY `uq_name` (`name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='平台预设资源表';

-- ----------------------------
-- Records of p_menu
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for p_role
-- ----------------------------
DROP TABLE IF EXISTS `p_role`;
CREATE TABLE `p_role` (
  `p_role_id` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '平台角色ID',
  `role_name` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '角色名称',
  `role_code` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '角色编码',
  `disabled` smallint(3) DEFAULT NULL COMMENT '是否禁用（0-否，1-是）',
  `deleted` smallint(3) DEFAULT NULL COMMENT '是否删除（0-否，1-是）',
  `remark` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_id` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '创建人ID',
  `create_user_name` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '创建人名称',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_user_id` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '修改人ID',
  `update_user_name` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '修改人名称',
  PRIMARY KEY (`p_role_id`),
  UNIQUE KEY `uq_role_tenant` (`role_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='平台预设角色表';

-- ----------------------------
-- Records of p_role
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for p_tenant
-- ----------------------------
DROP TABLE IF EXISTS `p_tenant`;
CREATE TABLE `p_tenant` (
  `tenant_id` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '租户唯一标识',
  `tenant_name` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '租户名称',
  `introduction` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '租户简称',
  `supper_person` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '租户联系人',
  `email` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '联系邮箱',
  `phone` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '联系电话',
  `supper_account` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '租户超级管理员账号',
  `supper_password` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '租户超级管理员密码',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`tenant_id`),
  UNIQUE KEY `uq_tennat_name` (`tenant_name`) USING BTREE,
  UNIQUE KEY `uq_introduction` (`introduction`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='租户表';

-- ----------------------------
-- Records of p_tenant
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `product_id` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '产品 ID',
  `product_name` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '产品名称',
  `is_free` smallint(3) DEFAULT NULL COMMENT '是否付费(1-付费，0-免费)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`product_id`),
  UNIQUE KEY `uq_product_name` (`product_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='产品';

-- ----------------------------
-- Records of product
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for resource_produce_module
-- ----------------------------
DROP TABLE IF EXISTS `resource_produce_module`;
CREATE TABLE `resource_produce_module` (
  `id` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '主键',
  `resource_id` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '资源菜单 ID',
  `product_id` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '产品 ID',
  `module_id` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '模块 ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of resource_produce_module
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for tenant_resource
-- ----------------------------
DROP TABLE IF EXISTS `tenant_resource`;
CREATE TABLE `tenant_resource` (
  `id` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL,
  `tenant_id` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '租户 ID',
  `product_id` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '产品 ID',
  `module_id` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '模块 ID',
  `menu_id` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '菜单 ID',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_idx` (`tenant_id`,`product_id`,`module_id`,`menu_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='租户产品模块菜单关联表';

-- ----------------------------
-- Records of tenant_resource
-- ----------------------------
BEGIN;
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
