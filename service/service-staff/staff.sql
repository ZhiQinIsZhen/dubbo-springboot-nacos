CREATE TABLE `customer` (
                            `customer_id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
                            `customer_name` varchar(32) NOT NULL,
                            `nick_name` varchar(16) NOT NULL,
                            `password` varchar(64) NOT NULL,
                            `mobile` varchar(16) NOT NULL,
                            `email` varchar(64) NOT NULL,
                            `is_inactive` tinyint(1) NOT NULL DEFAULT '0',
                            `create_time` datetime NOT NULL,
                            `update_time` datetime NOT NULL,
                            PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

CREATE TABLE `sta_login` (
                             `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
                             `customer_id` bigint(11) unsigned NOT NULL,
                             `device` int(1) unsigned NOT NULL DEFAULT '0' COMMENT '登陆设备(0：不区分；1：mobile；2：h5)',
                             `login_time` datetime NOT NULL COMMENT '登陆时间',
                             `create_time` datetime NOT NULL,
                             `update_time` datetime NOT NULL,
                             PRIMARY KEY (`id`) USING BTREE,
                             UNIQUE KEY `uqe_customer_id_device` (`customer_id`,`device`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


CREATE TABLE `sta_login_log` (
                                 `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
                                 `customer_id` bigint(11) unsigned NOT NULL,
                                 `device` int(1) NOT NULL COMMENT '登陆设备(0：不区分；1：mobile；2：h5)',
                                 `login_time` datetime NOT NULL,
                                 `login_ip` varchar(64) COLLATE utf8mb4_general_ci NOT NULL,
                                 `create_time` datetime NOT NULL,
                                 `update_time` datetime NOT NULL,
                                 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


CREATE TABLE `sys_role`  (
                             `id` int(8) UNSIGNED NOT NULL AUTO_INCREMENT,
                             `is_inactive` tinyint(1) NOT NULL DEFAULT 0,
                             `role_name` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                             `create_time` datetime(0) NULL,
                             `update_time` datetime(0) NULL,
                             PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10010002 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (10010001, 0, '管理员', '2020-08-17 16:49:13', '2020-08-17 16:49:15');
INSERT INTO `sys_role` VALUES (10010002, 0, '财务经理', '2021-08-30 14:42:39', '2021-08-30 14:42:41');

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
INSERT INTO `sys_permission` VALUES (2, '/user/info', 'ALL', '用户信息查询', 0, '2021-05-12 15:19:48', '2021-05-12 15:19:51');


CREATE TABLE `sys_role_permission`  (
                                        `role_id` int(8) NOT NULL,
                                        `permission_id` int(8) NOT NULL,
                                        `is_inactive` tinyint(1) NOT NULL DEFAULT 0,
                                        `create_time` datetime(0) NULL,
                                        `update_time` datetime(0) NULL,
                                        PRIMARY KEY (`role_id`, `permission_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES (10010001, 1, 0, '2020-08-17 16:50:12', '2020-08-17 16:50:14');
INSERT INTO `sys_role_permission` VALUES (10010001, 2, 0, '2021-08-30 14:43:19', '2021-08-30 14:43:23');
INSERT INTO `sys_role_permission` VALUES (10010002, 2, 0, '2021-05-12 15:20:04', '2021-05-12 15:20:07');



CREATE TABLE `customer_role`  (
                                  `id` bigint(11) NOT NULL COMMENT '主键',
                                  `customer_id` bigint(11) NOT NULL COMMENT '客户id',
                                  `role_id` int(8) NOT NULL COMMENT '角色id',
                                  `is_inactive` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否有效',
                                  `create_time` datetime(0) NULL,
                                  `update_time` datetime(0) NULL,
                                  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer_role
-- ----------------------------
INSERT INTO `customer_role` VALUES (1, 1, 10010001, 0, '2021-08-30 14:22:17', '2021-08-30 14:22:20');
INSERT INTO `customer_role` VALUES (2, 1, 10010002, 0, '2021-08-30 14:41:20', '2021-08-30 14:41:22');