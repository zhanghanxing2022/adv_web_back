/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80028
Source Host           : localhost:3306
Source Database       : adv_web

Target Server Type    : MYSQL
Target Server Version : 80028
File Encoding         : 65001

Date: 2023-06-15 00:59:52
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for algorithm
-- ----------------------------
DROP TABLE IF EXISTS `algorithm`;
CREATE TABLE `algorithm` (
  `name` varchar(255) DEFAULT '',
  UNIQUE KEY `algorithm_pk` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of algorithm
-- ----------------------------
INSERT INTO `algorithm` VALUES ('BST');
INSERT INTO `algorithm` VALUES ('HeapSort');
INSERT INTO `algorithm` VALUES ('MergeSort');
INSERT INTO `algorithm` VALUES ('QuickSort');
INSERT INTO `algorithm` VALUES ('二叉树遍历');
INSERT INTO `algorithm` VALUES ('冒泡排序');
INSERT INTO `algorithm` VALUES ('选择排序');

-- ----------------------------
-- Table structure for figure
-- ----------------------------
DROP TABLE IF EXISTS `figure`;
CREATE TABLE `figure` (
  `name` varchar(255) NOT NULL DEFAULT '',
  `skin` varchar(255) NOT NULL DEFAULT '',
  UNIQUE KEY `figure_pk` (`name`,`skin`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of figure
-- ----------------------------
INSERT INTO `figure` VALUES ('amy', 'amy');
INSERT INTO `figure` VALUES ('jimmy', 'jimmy');
INSERT INTO `figure` VALUES ('mouse', 'mouse');
INSERT INTO `figure` VALUES ('mouse', '强健体魄');
INSERT INTO `figure` VALUES ('rabbit', 'rabbit');
INSERT INTO `figure` VALUES ('rabbit', '粉色妖精小兔♪');
INSERT INTO `figure` VALUES ('rabbit', '黑旋风');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userID` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL DEFAULT '',
  `password` varchar(50) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`userID`),
  UNIQUE KEY `user_pk` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'kaiyudai', '12345678', 'kydai@fudan.edu.cn', '13666666666');
INSERT INTO `user` VALUES ('2', 'fengshuangli', '12345678', '13302010002@fudan.edu.cn', '13888888888');
INSERT INTO `user` VALUES ('4', 'new-user', 'abcdefg', '123@email.com', '123');
INSERT INTO `user` VALUES ('6', 'abc', 'ccc', 'a@b.com', '1234567890');
INSERT INTO `user` VALUES ('8', 'testUser', 'password', 'test@gmail.com', null);
INSERT INTO `user` VALUES ('9', 'jiadisu', 'password', 'jiadisu@gmail.com', '18317723039');
INSERT INTO `user` VALUES ('10', 'jeffery', 'password', 'pat@example.com', '1111111111');

-- ----------------------------
-- Table structure for user_algorithm
-- ----------------------------
DROP TABLE IF EXISTS `user_algorithm`;
CREATE TABLE `user_algorithm` (
  `userID` int NOT NULL,
  `algorithm` varchar(255) NOT NULL DEFAULT '',
  `type` enum('LEARN','PRACTICE') NOT NULL COMMENT '表示一次关于该算法的记录类型，可以是观看教学（0），也可以是尝试练习（1）',
  `time` datetime NOT NULL,
  UNIQUE KEY `user_algorithm_pk` (`userID`,`algorithm`,`time`),
  KEY `user_algorithm_algorithm_name_fk` (`algorithm`),
  CONSTRAINT `user_algorithm_algorithm_name_fk` FOREIGN KEY (`algorithm`) REFERENCES `algorithm` (`name`),
  CONSTRAINT `user_algorithm_user_userID_fk` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of user_algorithm
-- ----------------------------
INSERT INTO `user_algorithm` VALUES ('8', 'HeapSort', 'LEARN', '2023-06-11 16:02:08');
INSERT INTO `user_algorithm` VALUES ('8', 'HeapSort', 'PRACTICE', '2023-06-11 17:06:48');
INSERT INTO `user_algorithm` VALUES ('8', 'HeapSort', 'LEARN', '2023-06-11 20:23:14');
INSERT INTO `user_algorithm` VALUES ('8', 'HeapSort', 'LEARN', '2023-06-11 20:24:28');
INSERT INTO `user_algorithm` VALUES ('8', 'HeapSort', 'LEARN', '2023-06-11 20:25:56');
INSERT INTO `user_algorithm` VALUES ('8', 'MergeSort', 'LEARN', '2023-06-11 16:02:08');
INSERT INTO `user_algorithm` VALUES ('8', 'MergeSort', 'PRACTICE', '2023-06-11 17:06:24');
INSERT INTO `user_algorithm` VALUES ('8', 'MergeSort', 'LEARN', '2023-06-11 20:23:14');
INSERT INTO `user_algorithm` VALUES ('8', 'MergeSort', 'LEARN', '2023-06-11 20:24:28');
INSERT INTO `user_algorithm` VALUES ('8', 'MergeSort', 'LEARN', '2023-06-11 20:25:56');
INSERT INTO `user_algorithm` VALUES ('8', 'QuickSort', 'LEARN', '2023-06-11 15:49:15');
INSERT INTO `user_algorithm` VALUES ('8', 'QuickSort', 'LEARN', '2023-06-11 16:02:07');
INSERT INTO `user_algorithm` VALUES ('8', 'QuickSort', 'PRACTICE', '2023-06-11 16:58:11');
INSERT INTO `user_algorithm` VALUES ('8', 'QuickSort', 'LEARN', '2023-06-11 20:23:14');
INSERT INTO `user_algorithm` VALUES ('8', 'QuickSort', 'LEARN', '2023-06-11 20:24:28');
INSERT INTO `user_algorithm` VALUES ('8', 'QuickSort', 'LEARN', '2023-06-11 20:25:56');

-- ----------------------------
-- Table structure for user_figure
-- ----------------------------
DROP TABLE IF EXISTS `user_figure`;
CREATE TABLE `user_figure` (
  `userID` int NOT NULL,
  `figure` varchar(255) NOT NULL DEFAULT '',
  `skin` varchar(255) DEFAULT '',
  `order` int DEFAULT NULL,
  `time` datetime NOT NULL,
  UNIQUE KEY `table_name_pk` (`userID`,`figure`,`skin`,`order`),
  KEY `user_figure_figure_name_skin_fk` (`figure`,`skin`),
  CONSTRAINT `user_figure_figure_name_skin_fk` FOREIGN KEY (`figure`, `skin`) REFERENCES `figure` (`name`, `skin`),
  CONSTRAINT `user_figure_user_userID_fk` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of user_figure
-- ----------------------------
INSERT INTO `user_figure` VALUES ('8', 'amy', 'amy', '1', '2023-06-10 23:53:21');
INSERT INTO `user_figure` VALUES ('8', 'amy', 'amy', '2', '2023-06-10 23:56:05');
INSERT INTO `user_figure` VALUES ('8', 'rabbit', 'rabbit', '3', '2023-06-11 00:45:20');
INSERT INTO `user_figure` VALUES ('8', 'rabbit', 'rabbit', '4', '2023-06-11 00:46:08');
INSERT INTO `user_figure` VALUES ('8', 'rabbit', 'rabbit', '5', '2023-06-11 00:46:17');
INSERT INTO `user_figure` VALUES ('8', 'rabbit', '粉色妖精小兔♪', '6', '2023-06-11 01:07:52');
INSERT INTO `user_figure` VALUES ('8', 'rabbit', '粉色妖精小兔♪', '7', '2023-06-11 16:51:55');
INSERT INTO `user_figure` VALUES ('8', 'rabbit', '黑旋风', '8', '2023-06-11 16:55:36');
