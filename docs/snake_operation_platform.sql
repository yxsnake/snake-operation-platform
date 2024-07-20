/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1-123456
 Source Server Type    : MySQL
 Source Server Version : 50744 (5.7.44-log)
 Source Host           : localhost:3306
 Source Schema         : snake_operation_platform

 Target Server Type    : MySQL
 Target Server Version : 50744 (5.7.44-log)
 File Encoding         : 65001

 Date: 20/07/2024 20:40:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for p_api_resource
-- ----------------------------
DROP TABLE IF EXISTS `p_api_resource`;
CREATE TABLE `p_api_resource`  (
  `p_resource_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '平台资源ID',
  `parent_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '上级资源 ID',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '资源名称',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '路由路径(浏览器地址栏路径)',
  `perm` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '按钮权限标识',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  `sort` int(10) NULL DEFAULT NULL COMMENT '排序',
  `disabled` smallint(3) NULL DEFAULT NULL COMMENT '是否禁用（0-否，1-是）',
  `deleted` smallint(3) NULL DEFAULT NULL COMMENT '是否删除（0-否，1-是）',
  `issued_status` smallint(3) NULL DEFAULT 0 COMMENT '下发状态(0-未下发，1-已下发',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `create_user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '创建人ID',
  `create_user_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '创建人名称',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `update_user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '修改人ID',
  `update_user_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '修改人名称',
  PRIMARY KEY (`p_resource_id`) USING BTREE,
  UNIQUE INDEX `uq_perm`(`perm`) USING BTREE,
  UNIQUE INDEX `uq_path`(`path`) USING BTREE,
  UNIQUE INDEX `uq_name`(`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '平台预设API资源' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of p_api_resource
-- ----------------------------

-- ----------------------------
-- Table structure for p_menu
-- ----------------------------
DROP TABLE IF EXISTS `p_menu`;
CREATE TABLE `p_menu`  (
  `p_resource_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '平台资源ID',
  `parent_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '上级资源 ID',
  `resource_type` smallint(3) NULL DEFAULT NULL COMMENT '资源类型(0:目录,1:菜单, 2:按钮 ,3:外链,4:接口)',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '资源名称',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '路由路径(浏览器地址栏路径)',
  `component` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '组件路径(vue页面完整路径，省略.vue后缀)',
  `perm` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '按钮权限标识',
  `redirect` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '跳转路径',
  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  `sort` bigint(30) NULL DEFAULT NULL COMMENT '排序',
  `disabled` smallint(3) NULL DEFAULT NULL COMMENT '是否禁用（0-否，1-是）',
  `deleted` smallint(3) NULL DEFAULT NULL COMMENT '是否删除（0-否，1-是）',
  `level` smallint(3) NULL DEFAULT NULL COMMENT '层级（0～5， 最多支持 5 层）',
  `module_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '模块 ID',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `create_user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '创建人ID',
  `create_user_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '创建人名称',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `update_user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '修改人ID',
  `update_user_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '修改人名称',
  PRIMARY KEY (`p_resource_id`) USING BTREE,
  UNIQUE INDEX `uq_perm`(`perm`) USING BTREE,
  UNIQUE INDEX `uq_component`(`component`) USING BTREE,
  UNIQUE INDEX `uq_path`(`path`) USING BTREE,
  UNIQUE INDEX `uq_name`(`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '平台预设资源表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of p_menu
-- ----------------------------
INSERT INTO `p_menu` VALUES ('1814193437470113794', '0', 0, '系统管理', '/system', 'SystemManage', '', '', '1', '', 1721372396235, 0, 0, 1, '1814185517076529154', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `p_menu` VALUES ('1814194144076120065', '1814193437470113794', 1, '菜单管理', '/menu/list', 'MenuManage', 'menu:list', '', '1', '', 1721372564719, 0, 0, NULL, '1814185517076529154', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `p_menu` VALUES ('1814194288012050433', '1814193437470113794', 1, '角色管理', '/role/list', 'RoleManage', 'role:list', '', '1', '', 1721372599029, 0, 0, NULL, '1814185517076529154', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `p_menu` VALUES ('1814196049762336769', '1814193437470113794', 1, '员工管理', '/emp/list', 'EmpMange', 'emp:list', '', '1', '', 1721373019063, 0, 0, NULL, '1814185517076529154', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `p_menu` VALUES ('1814196118263709698', '1814193437470113794', 1, '组织架构管理', '/org/list', 'OrgMange', 'org:list', '', '1', '', 1721373035392, 0, 0, NULL, '1814185517076529154', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `p_menu` VALUES ('1814196331766366210', '1814193437470113794', 1, '会员管理', '/member/list', 'MemberMange', 'member:list', '', '1', '', 1721373086304, 0, 0, NULL, '1814185517076529154', NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for p_module
-- ----------------------------
DROP TABLE IF EXISTS `p_module`;
CREATE TABLE `p_module`  (
  `module_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '模块 ID',
  `product_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '模块 ID',
  `module_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '模块名称',
  `is_free` smallint(3) NULL DEFAULT NULL COMMENT '是否免费 (1:是，0:付费)',
  `deleted` smallint(3) NULL DEFAULT NULL COMMENT '删除标识(0-正常,1-删除)',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`module_id`) USING BTREE,
  UNIQUE INDEX `uq_module_name`(`module_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '模块' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of p_module
-- ----------------------------
INSERT INTO `p_module` VALUES ('1814185517076529154', '1814184809824600066', '系统基础设施模块', 0, 0, '2024-07-19 14:28:44');

-- ----------------------------
-- Table structure for p_product
-- ----------------------------
DROP TABLE IF EXISTS `p_product`;
CREATE TABLE `p_product`  (
  `product_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '产品 ID',
  `product_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '产品名称',
  `is_free` smallint(3) NULL DEFAULT NULL COMMENT '是否付费(1-付费，0-免费)',
  `deleted` smallint(3) NULL DEFAULT NULL COMMENT '删除标识(0-正常,1-删除)',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`product_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '产品' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of p_product
-- ----------------------------
INSERT INTO `p_product` VALUES ('1814184809824600066', '基础设施产品线', 0, 0, '2024-07-19 14:25:39');

-- ----------------------------
-- Table structure for p_role
-- ----------------------------
DROP TABLE IF EXISTS `p_role`;
CREATE TABLE `p_role`  (
  `p_role_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '平台角色ID',
  `role_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '角色名称',
  `role_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '角色编码',
  `disabled` smallint(3) NULL DEFAULT NULL COMMENT '是否禁用（0-否，1-是）',
  `deleted` smallint(3) NULL DEFAULT NULL COMMENT '是否删除（0-否，1-是）',
  `remark` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `create_user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '创建人ID',
  `create_user_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '创建人名称',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `update_user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '修改人ID',
  `update_user_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '修改人名称',
  PRIMARY KEY (`p_role_id`) USING BTREE,
  UNIQUE INDEX `uq_role_tenant`(`role_code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '平台预设角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of p_role
-- ----------------------------

-- ----------------------------
-- Table structure for p_tenant
-- ----------------------------
DROP TABLE IF EXISTS `p_tenant`;
CREATE TABLE `p_tenant`  (
  `tenant_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '租户唯一标识',
  `tenant_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '租户名称',
  `introduction` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '租户简称',
  `supper_person` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '租户联系人',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '联系邮箱',
  `phone` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '联系电话',
  `supper_account` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '租户超级管理员账号',
  `supper_password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '租户超级管理员密码',
  `audit_status` smallint(3) NULL DEFAULT NULL COMMENT '审核结论 (0-待审核同步，1-通过 , 2-驳回)',
  `audit_remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '审核意见',
  `deleted` smallint(3) NULL DEFAULT 0 COMMENT '删除标识(0-正常,1-删除)',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`tenant_id`) USING BTREE,
  UNIQUE INDEX `uq_tennat_name`(`tenant_name`) USING BTREE,
  UNIQUE INDEX `uq_introduction`(`introduction`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '租户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of p_tenant
-- ----------------------------
INSERT INTO `p_tenant` VALUES ('1814224657935519745', '北京银河创想信息技术有限公司', '银河创想', '张三', 'zhangsan@163.com', '18516908635', '20245459351119', '18516908635', 1, '通过', 0, '2024-07-19 17:04:02', NULL);

-- ----------------------------
-- Table structure for p_tenant_resource
-- ----------------------------
DROP TABLE IF EXISTS `p_tenant_resource`;
CREATE TABLE `p_tenant_resource`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `tenant_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '租户 ID',
  `product_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '产品 ID',
  `module_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '模块 ID',
  `menu_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '菜单 ID',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uq_idx`(`tenant_id`, `product_id`, `module_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '租户产品模块菜单关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of p_tenant_resource
-- ----------------------------
INSERT INTO `p_tenant_resource` VALUES ('1814224750113738754', '1814224657935519745', '1814184809824600066', '1814185517076529154', '1814193437470113794');
INSERT INTO `p_tenant_resource` VALUES ('1814224769709527041', '1814224657935519745', '1814184809824600066', '1814185517076529154', '1814194144076120065');
INSERT INTO `p_tenant_resource` VALUES ('1814224769709527042', '1814224657935519745', '1814184809824600066', '1814185517076529154', '1814194288012050433');
INSERT INTO `p_tenant_resource` VALUES ('1814224769709527043', '1814224657935519745', '1814184809824600066', '1814185517076529154', '1814196049762336769');
INSERT INTO `p_tenant_resource` VALUES ('1814224769709527044', '1814224657935519745', '1814184809824600066', '1814185517076529154', '1814196118263709698');
INSERT INTO `p_tenant_resource` VALUES ('1814224769709527045', '1814224657935519745', '1814184809824600066', '1814185517076529154', '1814196331766366210');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户ID',
  `username` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '密码',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '姓名',
  `gender` smallint(3) NULL DEFAULT NULL COMMENT '性别(0-女，1-男)',
  `status` smallint(3) NULL DEFAULT NULL COMMENT '账号状态(0-正常，1-禁用)',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '123456', '超级运营专员', 1, 0, '2024-07-20 13:50:06');

SET FOREIGN_KEY_CHECKS = 1;
