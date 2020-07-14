/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : localhost:3306
 Source Schema         : member

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 06/09/2019 17:13:01
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `nick_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '昵称',
  `user_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '真实姓名',
  `login_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录名',
  `login_pwd` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '登录密码',
  `mobile` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '手机号',
  `email` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '邮件',
  `reg_time` datetime(0) NOT NULL COMMENT '注册时间',
  `app_token_time` datetime(0) NULL DEFAULT NULL,
  `web_token_time` datetime(0) NULL DEFAULT NULL COMMENT 'JWT 强制失效时间',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `uniq_login_name`(`login_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES (1, '啦啦啦小魔仙', '张三', '15988654730', '$2a$10$j7FF83Bf.HbobebGITlmBeUbukBUeRUkie4CCEs0SBoXWeJ314DAK', '15988654730', '812672588@qq.com', '2019-08-28 17:28:34', '2019-08-27 17:28:37', '2019-09-06 16:59:50');
INSERT INTO `user_info` VALUES (2, '啦啦啦小魔仙', '张三', '15988654731', '$2a$10$j7FF83Bf.HbobebGITlmBeUbukBUeRUkie4CCEs0SBoXWeJ314DAK', '15988654730', '812672588@qq.com', '2019-08-28 17:28:34', '2019-08-27 17:28:37', '2019-08-28 20:36:55');
INSERT INTO `user_info` VALUES (3, '啦啦啦小魔仙', '张四', '15988654732', '$2a$10$j7FF83Bf.HbobebGITlmBeUbukBUeRUkie4CCEs0SBoXWeJ314DAK', '15988654730', '812672588@qq.com', '2019-08-28 17:28:34', '2019-08-27 17:28:37', '2019-08-28 20:36:55');
INSERT INTO `user_info` VALUES (4, '啦啦啦小魔仙', '张五', '15988654733', '$2a$10$j7FF83Bf.HbobebGITlmBeUbukBUeRUkie4CCEs0SBoXWeJ314DAK', '15988654730', '812672588@qq.com', '2019-08-28 17:28:34', '2019-08-27 17:28:37', '2019-08-28 20:36:55');
INSERT INTO `user_info` VALUES (5, '啦啦啦小魔仙', '张二', '15988654735', '$2a$10$j7FF83Bf.HbobebGITlmBeUbukBUeRUkie4CCEs0SBoXWeJ314DAK', '15988654730', '812672588@qq.com', '2019-08-28 17:28:34', '2019-08-27 17:28:37', '2019-08-28 20:36:55');
INSERT INTO `user_info` VALUES (6, '啦啦啦小魔仙', '张六', '15988654734', '$2a$10$j7FF83Bf.HbobebGITlmBeUbukBUeRUkie4CCEs0SBoXWeJ314DAK', '15988654730', '812672588@qq.com', '2019-08-28 17:28:34', '2019-08-27 17:28:37', '2019-08-28 20:36:55');
INSERT INTO `user_info` VALUES (7, '啦啦啦小魔仙', '张七', '15988654736', '$2a$10$j7FF83Bf.HbobebGITlmBeUbukBUeRUkie4CCEs0SBoXWeJ314DAK', '15988654730', '812672588@qq.com', '2019-08-28 17:28:34', '2019-08-27 17:28:37', '2019-08-28 20:36:55');
INSERT INTO `user_info` VALUES (8, '啦啦啦小魔仙', '张八', '15988654737', '$2a$10$j7FF83Bf.HbobebGITlmBeUbukBUeRUkie4CCEs0SBoXWeJ314DAK', '15988654730', '812672588@qq.com', '2019-08-28 17:28:34', '2019-08-27 17:28:37', '2019-08-28 20:36:55');
INSERT INTO `user_info` VALUES (9, '啦啦啦小魔仙', '张九', '15988654738', '$2a$10$j7FF83Bf.HbobebGITlmBeUbukBUeRUkie4CCEs0SBoXWeJ314DAK', '15988654730', '812672588@qq.com', '2019-08-28 17:28:34', '2019-08-27 17:28:37', '2019-08-28 20:36:55');
INSERT INTO `user_info` VALUES (10, '啦啦啦小魔仙', '张十', '15988654739', '$2a$10$j7FF83Bf.HbobebGITlmBeUbukBUeRUkie4CCEs0SBoXWeJ314DAK', '15988654730', '812672588@qq.com', '2019-08-28 17:28:34', '2019-08-27 17:28:37', '2019-08-28 20:36:55');
INSERT INTO `user_info` VALUES (11, '啦啦啦小魔仙', '张十一', '15988654740', '$2a$10$j7FF83Bf.HbobebGITlmBeUbukBUeRUkie4CCEs0SBoXWeJ314DAK', '15988654730', '812672588@qq.com', '2019-08-28 17:28:34', '2019-08-27 17:28:37', '2019-08-28 20:36:55');
INSERT INTO `user_info` VALUES (12, '啦啦啦小魔仙', '张十二', '15988654741', '$2a$10$j7FF83Bf.HbobebGITlmBeUbukBUeRUkie4CCEs0SBoXWeJ314DAK', '15988654730', '812672588@qq.com', '2019-08-28 17:28:34', '2019-08-27 17:28:37', '2019-08-28 20:36:55');
INSERT INTO `user_info` VALUES (13, '啦啦啦小魔仙', '张十三', '15988654742', '$2a$10$j7FF83Bf.HbobebGITlmBeUbukBUeRUkie4CCEs0SBoXWeJ314DAK', '15988654730', '812672588@qq.com', '2019-08-28 17:28:34', '2019-08-27 17:28:37', '2019-08-28 20:36:55');

-- ----------------------------
-- Table structure for user_login_log
-- ----------------------------
DROP TABLE IF EXISTS `user_login_log`;
CREATE TABLE `user_login_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `type` tinyint(1) NOT NULL COMMENT '0:注册；1：登陆',
  `ip` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `device` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_time` datetime(0) NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_user_id`(`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;