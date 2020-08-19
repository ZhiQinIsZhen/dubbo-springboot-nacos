/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : localhost:3306
 Source Schema         : rule

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 19/08/2020 09:42:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer`  (
  `customer_id` bigint(11) NOT NULL,
  `customer_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `nick_name` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `role_id` int(8) NOT NULL,
  `mobile` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `is_inactive` tinyint(1) NOT NULL DEFAULT 0,
  `app_token_time` datetime(0) NULL DEFAULT NULL,
  `web_token_time` datetime(0) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL,
  `update_time` datetime(0) NULL,
  PRIMARY KEY (`customer_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES (1, '15988654730', '啦啦', '$2a$10$j7FF83Bf.HbobebGITlmBeUbukBUeRUkie4CCEs0SBoXWeJ314DAK', 1, '15988654730', '812672598@qq.com', 0, '2020-08-18 15:20:27', '2020-08-18 20:20:31', '2020-08-17 16:48:31', '2020-08-17 16:48:34');

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
  `permission_id` int(8) NOT NULL,
  `permission_url` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `method` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `permission_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `is_inactive` tinyint(1) NOT NULL DEFAULT 0,
  `create_time` datetime(0) NULL,
  `update_time` datetime(0) NULL,
  PRIMARY KEY (`permission_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES (1, '/user/page', 'ALL', '分页查询用户信息', 0, '2020-08-17 16:50:00', '2020-08-17 16:50:03');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` int(8) UNSIGNED NOT NULL AUTO_INCREMENT,
  `is_inactive` tinyint(1) NOT NULL DEFAULT 0,
  `role_name` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` datetime(0) NULL,
  `update_time` datetime(0) NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, 0, '管理员', '2020-08-17 16:49:13', '2020-08-17 16:49:15');

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission`  (
  `role_id` int(8) NOT NULL,
  `permission_id` int(8) NOT NULL,
  `is_inactive` tinyint(1) NOT NULL DEFAULT 0,
  `create_time` datetime(0) NULL,
  `update_time` datetime(0) NULL,
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES (1, 1, 0, '2020-08-17 16:50:12', '2020-08-17 16:50:14');

SET FOREIGN_KEY_CHECKS = 1;
