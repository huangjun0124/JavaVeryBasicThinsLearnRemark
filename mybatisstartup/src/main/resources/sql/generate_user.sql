/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.109.128
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : 192.168.109.128:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 05/04/2019 16:57:47
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `UserName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Sex` int(11) NULL DEFAULT NULL,
  `AccountType` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('a6d9ec6ce4f9428f8dd7de41f54afa06', 'keith', 99, 3);
INSERT INTO `user` VALUES ('b70ad327340640b890cd5a67426de48a', 'jun', 66, 1);
INSERT INTO `user` VALUES ('b70ad327340640b890cd5a67426de666', 'keven', 99, 0);
INSERT INTO `user` VALUES ('b70ad327340640b890cd5a67426de889', 'kege', 66, 2);
INSERT INTO `user` VALUES ('e1b32e7d593e4fb1aa57788db10ba784', 'Chris', 66, 3);
INSERT INTO `user` VALUES ('ec63fc032ee34a1bbed7d4b4e51383f3', 'keloris', 99, 2);

SET FOREIGN_KEY_CHECKS = 1;
