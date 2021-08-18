/*
Navicat MySQL Data Transfer

Source Server         : mysql8.0
Source Server Version : 80021
Source Host           : localhost:3306
Source Database       : plane

Target Server Type    : MYSQL
Target Server Version : 80021
File Encoding         : 65001

Date: 2021-05-18 20:58:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `account` varchar(10) NOT NULL,
  `password` varchar(20) NOT NULL,
  `name` varchar(10) NOT NULL,
  PRIMARY KEY (`account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('admin', 'admin123', '管理员');

-- ----------------------------
-- Table structure for classes
-- ----------------------------
DROP TABLE IF EXISTS `classes`;
CREATE TABLE `classes` (
  `classNumber` varchar(10) NOT NULL,
  `DepartureDate` datetime NOT NULL,
  `DepartureAirport` varchar(10) NOT NULL,
  `ArrivalDate` datetime NOT NULL,
  `ArrivalAirport` varchar(10) NOT NULL,
  `A_number` int NOT NULL,
  `delay` int NOT NULL DEFAULT '0',
  `B_number` int NOT NULL,
  `APrice` int NOT NULL,
  `BPrice` int NOT NULL,
  `flightNumber` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`classNumber`),
  KEY `classes_flight_flight_number_fk` (`flightNumber`),
  CONSTRAINT `classes_flight_flight_number_fk` FOREIGN KEY (`flightNumber`) REFERENCES `flight` (`flightNumber`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of classes
-- ----------------------------
INSERT INTO `classes` VALUES ('4645', '2021-05-15 10:00:00', '唐山', '2021-05-15 10:30:00', '添加', '30', '0', '80', '300', '100', 'CA1206');
INSERT INTO `classes` VALUES ('TS8552', '2021-05-15 19:03:00', '唐山', '2021-05-15 20:15:00', '石家庄', '28', '0', '80', '499', '298', 'CS5546');

-- ----------------------------
-- Table structure for flight
-- ----------------------------
DROP TABLE IF EXISTS `flight`;
CREATE TABLE `flight` (
  `companyName` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `planeType` varchar(20) NOT NULL,
  `flightNumber` varchar(10) NOT NULL,
  PRIMARY KEY (`flightNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of flight
-- ----------------------------
INSERT INTO `flight` VALUES ('中国国际航空', '空客A319', 'CA1206');
INSERT INTO `flight` VALUES ('中国国际航空', '空客A320', 'CA5463');
INSERT INTO `flight` VALUES ('中国国际航空', '波音737', 'CA8524');
INSERT INTO `flight` VALUES ('中国东方航空', '波音747', 'CE4562');
INSERT INTO `flight` VALUES ('中国东方航空', '空客A321', 'CE9652');
INSERT INTO `flight` VALUES ('中国南方航空', '空客A380', 'CS5546');

-- ----------------------------
-- Table structure for passengers
-- ----------------------------
DROP TABLE IF EXISTS `passengers`;
CREATE TABLE `passengers` (
  `orderNumber` int NOT NULL AUTO_INCREMENT,
  `name` varchar(10) NOT NULL,
  `id` varchar(20) NOT NULL,
  `orderTime` datetime NOT NULL,
  `seat` varchar(5) NOT NULL,
  `account` varchar(10) NOT NULL,
  `degree` varchar(2) NOT NULL,
  `classNumber` varchar(10) NOT NULL,
  PRIMARY KEY (`orderNumber`),
  KEY `passengers_classes_classNumber_fk` (`classNumber`),
  KEY `passengers_users_account_fk` (`account`),
  CONSTRAINT `passengers_classes_classNumber_fk` FOREIGN KEY (`classNumber`) REFERENCES `classes` (`classNumber`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `passengers_users_account_fk` FOREIGN KEY (`account`) REFERENCES `users` (`account`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of passengers
-- ----------------------------
INSERT INTO `passengers` VALUES ('1', '爱国合适的', '8795467865', '2021-05-18 19:43:19', 'A01', 'test', 'A', 'TS8552');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `account` varchar(10) NOT NULL,
  `sex` varchar(2) DEFAULT NULL,
  `password` varchar(20) NOT NULL,
  `age` int DEFAULT NULL,
  `tel` varchar(20) DEFAULT NULL,
  `name` varchar(10) NOT NULL,
  PRIMARY KEY (`account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('abc', '男', '1234', '18', '111111111', 'lll');
INSERT INTO `users` VALUES ('abcd', null, '123', '12', '12131321321', '12');
INSERT INTO `users` VALUES ('abcddd', null, '123', '12', '123131', '12');
INSERT INTO `users` VALUES ('abdd', '男', '1234', '18', '111111', '乐乐');
INSERT INTO `users` VALUES ('test', '女', '001122', '25', '14485229632', '张玉佳');
INSERT INTO `users` VALUES ('test3', null, '123', '12', '1112', '122');
INSERT INTO `users` VALUES ('test9', null, '9999', '19', '111222333', '乐乐');
