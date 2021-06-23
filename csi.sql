/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : localhost:3306
 Source Schema         : csi

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 23/06/2021 16:35:02
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dept_inf
-- ----------------------------
DROP TABLE IF EXISTS `dept_inf`;
CREATE TABLE `dept_inf`  (
  `ID` int NOT NULL,
  `NAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `REMARK` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of dept_inf
-- ----------------------------

-- ----------------------------
-- Table structure for employee_inf
-- ----------------------------
DROP TABLE IF EXISTS `employee_inf`;
CREATE TABLE `employee_inf`  (
  `ID` int NOT NULL,
  `DEPT_ID` int NULL DEFAULT NULL,
  `JOB_ID` int NULL DEFAULT NULL,
  `NAME` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CARD_ID` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ADDRESS` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `POST_CODE` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TEL` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `PHONE` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `QQ_NUM` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `EMAIL` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `SEX` int NOT NULL,
  `PARTY` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `BIRTHDAY` datetime(0) NOT NULL,
  `RACE` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `EDUCATION` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `SPECIALITY` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `HOBBY` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `REMARK` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `CREATE_DATE` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `eduction` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `DEPT_ID`(`DEPT_ID`) USING BTREE,
  INDEX `JOB_ID`(`JOB_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of employee_inf
-- ----------------------------

-- ----------------------------
-- Table structure for facekey_inf
-- ----------------------------
DROP TABLE IF EXISTS `facekey_inf`;
CREATE TABLE `facekey_inf`  (
  `ID` int NOT NULL,
  `base64` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of facekey_inf
-- ----------------------------

-- ----------------------------
-- Table structure for job_inf
-- ----------------------------
DROP TABLE IF EXISTS `job_inf`;
CREATE TABLE `job_inf`  (
  `ID` int NOT NULL,
  `NAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `REMARK` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remake` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of job_inf
-- ----------------------------

-- ----------------------------
-- Table structure for local_storage
-- ----------------------------
DROP TABLE IF EXISTS `local_storage`;
CREATE TABLE `local_storage`  (
  `storage_id` int NOT NULL,
  `real_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `suffix` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `size` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_time` timestamp(0) NULL DEFAULT NULL,
  `update_time` timestamp(0) NULL DEFAULT NULL,
  `detail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`storage_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of local_storage
-- ----------------------------
INSERT INTO `local_storage` VALUES (1024, '47687676 (1)-20210622033553417.jpeg', '231222', 'jpeg', 'C:\\doghome\\file\\图片\\47687676 (1)-20210622033553417.jpeg', '图片', '17.75KB   ', 'Super admin', NULL, '2021-06-22 15:35:53', NULL, '213222');
INSERT INTO `local_storage` VALUES (1093, 'csi-20210622114329290.sql', '321', 'sql', 'C:\\doghome\\file\\其他\\csi-20210622114329290.sql', '其他', '7.69KB   ', 'Super admin', NULL, '2021-06-22 11:43:29', NULL, '312');

-- ----------------------------
-- Table structure for notice_inf
-- ----------------------------
DROP TABLE IF EXISTS `notice_inf`;
CREATE TABLE `notice_inf`  (
  `ID` int NOT NULL,
  `TITLE` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CONTENT` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CREATE_DATE` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `USER_ID` int NULL DEFAULT NULL,
  `caredate` date NULL DEFAULT NULL,
  `tiitle` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `userid` int NOT NULL,
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `USER_ID`(`USER_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of notice_inf
-- ----------------------------

-- ----------------------------
-- Table structure for test_inf
-- ----------------------------
DROP TABLE IF EXISTS `test_inf`;
CREATE TABLE `test_inf`  (
  `id` int NOT NULL,
  `info` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `time` timestamp(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of test_inf
-- ----------------------------
INSERT INTO `test_inf` VALUES (2, '22', '2021-06-20 18:49:34');
INSERT INTO `test_inf` VALUES (3, '33', '2021-06-18 16:14:52');
INSERT INTO `test_inf` VALUES (4, '44', '2021-06-20 18:50:09');
INSERT INTO `test_inf` VALUES (5, '55', '2021-06-20 18:50:21');
INSERT INTO `test_inf` VALUES (6, '66', '2021-06-20 18:50:31');
INSERT INTO `test_inf` VALUES (7, '77', '2021-06-20 18:50:46');
INSERT INTO `test_inf` VALUES (8, '88', '2021-06-20 18:50:58');
INSERT INTO `test_inf` VALUES (9, '99', '2021-06-20 18:51:17');
INSERT INTO `test_inf` VALUES (10, '1010', '2021-06-20 18:51:31');
INSERT INTO `test_inf` VALUES (11, '1111', '2021-06-20 18:51:43');
INSERT INTO `test_inf` VALUES (1041, '213123', '2021-06-21 00:00:00');
INSERT INTO `test_inf` VALUES (1062, '11', '2021-06-21 00:00:00');

-- ----------------------------
-- Table structure for user_inf
-- ----------------------------
DROP TABLE IF EXISTS `user_inf`;
CREATE TABLE `user_inf`  (
  `ID` int NOT NULL,
  `Loginname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `PASSWORD` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `STATUS` int NOT NULL,
  `Createdate` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `Username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Faceurl` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Facepath` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user_inf
-- ----------------------------
INSERT INTO `user_inf` VALUES (0, 'admin', '111111', 1, NULL, 'ADMIN', NULL, NULL);
INSERT INTO `user_inf` VALUES (1, 'editor', '111111', 2, NULL, 'EDITOR', NULL, NULL);
INSERT INTO `user_inf` VALUES (2, 'sb', '111111', 2, '2021-06-23 00:00:00', 'SB', NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
