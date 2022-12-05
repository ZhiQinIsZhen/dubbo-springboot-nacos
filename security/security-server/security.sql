CREATE TABLE `user_algorithm` (
                                  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
                                  `user_id` int(11) NOT NULL COMMENT '用户id',
                                  `algorithm` varchar(32) COLLATE utf8mb4_general_ci NOT NULL COMMENT '加密算法',
                                  `type` tinyint(1) NOT NULL COMMENT '0:对称加密；1：非对称加密',
                                  `private_key` longblob COMMENT '私钥',
                                  `public_key` longblob COMMENT '公钥',
                                  `algorithm_key` longblob COMMENT '秘钥',
                                  `iv` longblob,
                                  `is_inactive` tinyint(1) unsigned NOT NULL DEFAULT '0',
                                  `create_time` datetime NOT NULL,
                                  `update_time` datetime NOT NULL,
                                  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;