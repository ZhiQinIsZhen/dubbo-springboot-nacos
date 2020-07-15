/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : localhost:3306
 Source Schema         : sms

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 15/07/2020 16:32:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for msg_template
-- ----------------------------
DROP TABLE IF EXISTS `msg_template`;
CREATE TABLE `msg_template`  (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `code` int(8) NOT NULL,
  `type` tinyint(1) NOT NULL COMMENT '类型：0：短信；1：邮件',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `content` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `locale` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uni_code_type`(`code`, `type`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of msg_template
-- ----------------------------
INSERT INTO `msg_template` VALUES (1, 10020001, 1, '规则报警', '<div style=\"border:1px solid #CCC;background:#F4F4F4;width:100%;text-align:left\"><div style=\"border:none;background:#FCFCFC;padding:20px;color:#333;font-size:14px;\"><p>您好！</p><p>您设立的规则：<span style=\"color:#c83935;font-size:16px\">${ruleName}</span></p><p>未满足要求，请确认具体情况。<p>感謝您的支持！</p><p>仟金顶</p></div></div><br><br></div>', 'zh_CN');
INSERT INTO `msg_template` VALUES (2, 2, 1, '注册验证码', '<div style=\"border:1px solid #CCC;background:#F4F4F4;width:100%;text-align:left\"><div style=\"border:none;background:#FCFCFC;padding:20px;color:#333;font-size:14px;\"><p>您好！</p><p>您注册的验证码：<span style=\"color:#c83935;font-size:16px\">${code}</span></p><p>有效期10分钟。<p>感謝您的支持！</p><p>仟金顶</p></div></div><br><br></div>', 'zh_CN');
INSERT INTO `msg_template` VALUES (3, 1, 1, '登陆验证码', '<div style=\"border:1px solid #CCC;background:#F4F4F4;width:100%;text-align:left\"><div style=\"border:none;background:#FCFCFC;padding:20px;color:#333;font-size:14px;\"><p>您好！</p><p>您登陆的验证码：<span style=\"color:#c83935;font-size:16px\">${code}</span></p><p>有效期10分钟。<p>感謝您的支持！</p><p>仟金顶</p></div></div><br><br></div>', 'zh_CN');


DROP TABLE IF EXISTS `message_log`;
CREATE TABLE `message_log`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `biz_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `code` int(8) NOT NULL,
  `type` tinyint(1) NOT NULL,
  `subject` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `success` tinyint(1) NOT NULL COMMENT '是否成功',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_time` datetime(0) NULL,
  `update_time` datetime(0) NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;


SET FOREIGN_KEY_CHECKS = 1;
