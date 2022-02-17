/*
Navicat MySQL Data Transfer

Source Server         : study
Source Server Version : 80021
Source Host           : localhost:3306
Source Database       : file

Target Server Type    : MYSQL
Target Server Version : 80021
File Encoding         : 65001

Date: 2022-02-17 11:17:44
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for file_system
-- ----------------------------
DROP TABLE IF EXISTS `file_system`;
CREATE TABLE `file_system` (
  `fileName` varchar(255) DEFAULT NULL,
  `fileContent` varchar(255) DEFAULT NULL,
  `id` varchar(255) NOT NULL,
  `oldName` varchar(255) DEFAULT NULL,
  `creatTime` varchar(255) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `fileSize` double(255,0) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
