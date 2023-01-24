/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50720
 Source Host           : localhost:3306
 Source Schema         : reactspringbootdb

 Target Server Type    : MySQL
 Target Server Version : 50720
 File Encoding         : 65001

 Date: 24/01/2023 12:32:11
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for app_user
-- ----------------------------
DROP TABLE IF EXISTS `app_user`;
CREATE TABLE `app_user`  (
  `user_no` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'User No',
  `user_login_name` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'user login name,nickname',
  `user_passwd` varchar(400) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'user login password',
  `user_true_name` varchar(800) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'user true name',
  `user_role` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'user role',
  PRIMARY KEY (`user_no`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of app_user
-- ----------------------------
INSERT INTO `app_user` VALUES (1, 'admin', 'e10adc3949ba59abbe56e057f20f883e', 'administrator', 'admin');
INSERT INTO `app_user` VALUES (2, 'addison', 'e10adc3949ba59abbe56e057f20f883e', 'Addison', 'manager');
INSERT INTO `app_user` VALUES (3, 'barry', 'e10adc3949ba59abbe56e057f20f883e', 'Barry', 'manager');
INSERT INTO `app_user` VALUES (4, 'barton', 'e10adc3949ba59abbe56e057f20f883e', 'Barton', 'manager');
INSERT INTO `app_user` VALUES (5, 'hogan', 'e10adc3949ba59abbe56e057f20f883e', 'Hogan', 'manager');
INSERT INTO `app_user` VALUES (6, 'jack', 'e10adc3949ba59abbe56e057f20f883e', 'Jack', 'manager');
INSERT INTO `app_user` VALUES (7, 'lambert', 'e10adc3949ba59abbe56e057f20f883e', 'Lambert', 'manager');
INSERT INTO `app_user` VALUES (8, 'lisi', 'e10adc3949ba59abbe56e057f20f883e', '李四', 'manager');
INSERT INTO `app_user` VALUES (9, 'kimseokjae', 'e10adc3949ba59abbe56e057f20f883e', '김석재', 'manager');
INSERT INTO `app_user` VALUES (10, 'zhangsan', 'e10adc3949ba59abbe56e057f20f883e', '张三', 'manager');
INSERT INTO `app_user` VALUES (11, 'parkeunsil', 'e10adc3949ba59abbe56e057f20f883e', '박은실', 'manager');

SET FOREIGN_KEY_CHECKS = 1;
