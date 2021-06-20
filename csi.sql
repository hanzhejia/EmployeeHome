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

 Date: 20/06/2021 14:29:27
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
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dept_inf
-- ----------------------------

-- ----------------------------
-- Table structure for document_inf
-- ----------------------------
DROP TABLE IF EXISTS `document_inf`;
CREATE TABLE `document_inf`  (
  `ID` int NOT NULL,
  `TITLE` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Filename` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `REMARK` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `CREATE_DATE` timestamp(0) NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP(0),
  `USER_ID` int NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `USER_ID_1`(`USER_ID`) USING BTREE,
  CONSTRAINT `USER_ID_1` FOREIGN KEY (`USER_ID`) REFERENCES `user_inf` (`ID`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of document_inf
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
  `CREATE_DATE` timestamp(0) NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `DEPT_ID`(`DEPT_ID`) USING BTREE,
  INDEX `JOB_ID`(`JOB_ID`) USING BTREE,
  CONSTRAINT `DEPT_ID_FK` FOREIGN KEY (`DEPT_ID`) REFERENCES `dept_inf` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `JOB_ID_FK` FOREIGN KEY (`JOB_ID`) REFERENCES `job_inf` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `USER_ID_FK` FOREIGN KEY (`ID`) REFERENCES `user_inf` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of employee_inf
-- ----------------------------

-- ----------------------------
-- Table structure for facekey_inf
-- ----------------------------
DROP TABLE IF EXISTS `facekey_inf`;
CREATE TABLE `facekey_inf`  (
  `ID` int NOT NULL,
  `appID` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `apiKey` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `secretKey` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `threshold` int NOT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of job_inf
-- ----------------------------

-- ----------------------------
-- Table structure for notice_inf
-- ----------------------------
DROP TABLE IF EXISTS `notice_inf`;
CREATE TABLE `notice_inf`  (
  `ID` int NOT NULL,
  `TITLE` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CONTENT` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CREATE_DATE` timestamp(0) NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP(0),
  `USER_ID` int NULL DEFAULT NULL,
  `caredate` date NULL DEFAULT NULL,
  `tiitle` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `userid` int NOT NULL,
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `USER_ID`(`USER_ID`) USING BTREE,
  CONSTRAINT `USER_ID` FOREIGN KEY (`USER_ID`) REFERENCES `user_inf` (`ID`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of test_inf
-- ----------------------------
INSERT INTO `test_inf` VALUES (1, '2', '2021-06-19 16:14:31');
INSERT INTO `test_inf` VALUES (3, '4', '2021-06-18 16:14:52');

-- ----------------------------
-- Table structure for user_inf
-- ----------------------------
DROP TABLE IF EXISTS `user_inf`;
CREATE TABLE `user_inf`  (
  `ID` int NOT NULL,
  `Loginname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `PASSWORD` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `STATUS` int NOT NULL,
  `Createdate` timestamp(0) NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP(0),
  `Username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Faceurl` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Facepath` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_inf
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
