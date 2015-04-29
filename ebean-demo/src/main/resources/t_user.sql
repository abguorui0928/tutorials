SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) COLLATE utf8_bin NOT NULL,
  `active` char(1) COLLATE utf8_bin NOT NULL,
  `create_date` datetime NOT NULL,
  `update_date` datetime NOT NULL,
  `display_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `email_address` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `credential` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_user_name` (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;