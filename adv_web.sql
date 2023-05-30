/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80028
Source Host           : localhost:3306
Source Database       : adv_web

Target Server Type    : MYSQL
Target Server Version : 80028
File Encoding         : 65001

Date: 2023-05-30 16:53:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for images
-- ----------------------------
DROP TABLE IF EXISTS `images`;
CREATE TABLE `images` (
  `name` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of images
-- ----------------------------
INSERT INTO `images` VALUES ('fireman');

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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'kaiyudai', '12345678', 'kydai@fudan.edu.cn', '13666666666');
INSERT INTO `user` VALUES ('2', 'fengshuangli', '12345678', '13302010002@fudan.edu.cn', '13888888888');
INSERT INTO `user` VALUES ('3', 'zhongyitong', '12345678', null, null);
INSERT INTO `user` VALUES ('4', 'new-user', 'abcdefg', '123@email.com', '123');
INSERT INTO `user` VALUES ('5', 'new-user2', 'abcdefg', '123@email.com', '123');
INSERT INTO `user` VALUES ('6', 'abc', 'ccc', 'a@b.com', '1234567890');
INSERT INTO `user` VALUES ('7', 'new-user3', 'abcdefg', '123@email.com', '123');

-- ----------------------------
-- Table structure for user_images
-- ----------------------------
DROP TABLE IF EXISTS `user_images`;
CREATE TABLE `user_images` (
  `username` varchar(255) NOT NULL,
  `image` varchar(255) NOT NULL,
  `index` int NOT NULL,
  UNIQUE KEY `user_images_pk` (`username`,`image`,`index`),
  KEY `user_images_images_name_fk` (`image`),
  CONSTRAINT `user_images_images_name_fk` FOREIGN KEY (`image`) REFERENCES `images` (`name`),
  CONSTRAINT `user_images_user_username_fk` FOREIGN KEY (`username`) REFERENCES `user` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of user_images
-- ----------------------------
INSERT INTO `user_images` VALUES ('new-user3', 'fireman', '1');
INSERT INTO `user_images` VALUES ('new-user3', 'fireman', '2');
INSERT INTO `user_images` VALUES ('new-user3', 'fireman', '3');
INSERT INTO `user_images` VALUES ('new-user3', 'fireman', '4');
