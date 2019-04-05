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

 Date: 05/04/2019 16:57:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `RoleName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Note` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('216e8f7c28a846a78de10d7b8e1acf34', 'testmap', '2019-04-05 16:27:33');
INSERT INTO `role` VALUES ('3c12bab2bc2e49e193b6ce621c9e6aab', 'testmap', '2019-04-05 16:26:36');
INSERT INTO `role` VALUES ('81494e11f3f6422983d3517a8f8aff6f', 'testmap', '2019-04-05 16:24:14');
INSERT INTO `role` VALUES ('9e4e9554a0134b5bb62e9486f9319318', 'normal', '2019-04-05 08:24:05');
INSERT INTO `role` VALUES ('bec7230480c4480bb4c85a79b3f741cd', 'testupdate', '2019-04-05 08:31:48');
INSERT INTO `role` VALUES ('cda3e3a9c4aa415da505920ffc3afe2a', 'testupdatenew', '2019-04-05 08:31:48');
INSERT INTO `role` VALUES ('d4ef435531cb40e6a7d425556b1dc7ce', 'admin', '2019-04-05 08:23:54');

SET FOREIGN_KEY_CHECKS = 1;
