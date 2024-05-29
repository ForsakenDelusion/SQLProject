/*
 Navicat Premium Data Transfer

 Source Server         : Mysql
 Source Server Type    : MySQL
 Source Server Version : 80400 (8.4.0)
 Source Host           : localhost:3306
 Source Schema         : Demo

 Target Server Type    : MySQL
 Target Server Version : 80400 (8.4.0)
 File Encoding         : 65001

 Date: 30/05/2024 03:10:46
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for Admin
-- ----------------------------
DROP TABLE IF EXISTS `Admin`;
CREATE TABLE `Admin` (
  `Aid` bigint NOT NULL AUTO_INCREMENT,
  `Apassword` varchar(255) NOT NULL,
  PRIMARY KEY (`Aid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for BR
-- ----------------------------
DROP TABLE IF EXISTS `BR`;
CREATE TABLE `BR` (
  `BRid` bigint NOT NULL AUTO_INCREMENT,
  `BRUid` bigint NOT NULL,
  `BRDid` bigint NOT NULL,
  `BRbdate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `BRrdate` datetime DEFAULT NULL,
  PRIMARY KEY (`BRid`),
  KEY `f.BRDid` (`BRDid`),
  KEY `f.BRUid` (`BRUid`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for Device
-- ----------------------------
DROP TABLE IF EXISTS `Device`;
CREATE TABLE `Device` (
  `Did` bigint NOT NULL AUTO_INCREMENT,
  `Dname` varchar(255) NOT NULL,
  `Dstatus` enum('空闲中','使用中','维修中') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '空闲中',
  `Dprice` decimal(10,2) NOT NULL,
  `Ddate` date DEFAULT NULL,
  PRIMARY KEY (`Did`),
  KEY `Dname` (`Dname`)
) ENGINE=InnoDB AUTO_INCREMENT=20240529151137 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for Fixer
-- ----------------------------
DROP TABLE IF EXISTS `Fixer`;
CREATE TABLE `Fixer` (
  `Fid` bigint NOT NULL AUTO_INCREMENT,
  `Fpassword` varchar(255) NOT NULL,
  `Fname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Ftele` bigint DEFAULT NULL,
  PRIMARY KEY (`Fid`) USING BTREE,
  KEY `Fname` (`Fname`),
  KEY `Ftele` (`Ftele`)
) ENGINE=InnoDB AUTO_INCREMENT=2023 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for Maintain
-- ----------------------------
DROP TABLE IF EXISTS `Maintain`;
CREATE TABLE `Maintain` (
  `Mid` bigint NOT NULL AUTO_INCREMENT,
  `MFid` bigint DEFAULT NULL,
  `MDid` bigint NOT NULL,
  `MdateBegin` datetime DEFAULT CURRENT_TIMESTAMP,
  `MdateEnd` datetime DEFAULT NULL,
  PRIMARY KEY (`Mid`) USING BTREE,
  KEY `f.MFid` (`MFid`),
  KEY `f.MDid` (`MDid`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for User
-- ----------------------------
DROP TABLE IF EXISTS `User`;
CREATE TABLE `User` (
  `Uid` bigint NOT NULL AUTO_INCREMENT,
  `Upassword` varchar(255) NOT NULL,
  `Uname` varchar(255) NOT NULL,
  PRIMARY KEY (`Uid`)
) ENGINE=InnoDB AUTO_INCREMENT=2334 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Triggers structure for table BR
-- ----------------------------
DROP TRIGGER IF EXISTS `Borrow`;
delimiter ;;
CREATE TRIGGER `Borrow` BEFORE INSERT ON `BR` FOR EACH ROW UPDATE Device SET Dstatus = '使用中' where Did = new.BRDid
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table Device
-- ----------------------------
DROP TRIGGER IF EXISTS `SetDid`;
delimiter ;;
CREATE TRIGGER `SetDid` BEFORE INSERT ON `Device` FOR EACH ROW SET NEW.Did = CONCAT(YEAR(CURRENT_DATE), LPAD(MONTH(CURRENT_DATE), 2, '0'), LPAD(DAY(CURRENT_DATE), 2, '0'), LPAD(HOUR(CURRENT_TIME), 2, '0'), LPAD(MINUTE(CURRENT_TIME), 2, '0'), LPAD(SECOND(CURRENT_TIME), 2, '0'))
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table Device
-- ----------------------------
DROP TRIGGER IF EXISTS `SetDate`;
delimiter ;;
CREATE TRIGGER `SetDate` BEFORE INSERT ON `Device` FOR EACH ROW SET new.Ddate = CURRENT_DATE
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table Device
-- ----------------------------
DROP TRIGGER IF EXISTS `UpdateMaintainStatusToIdle`;
delimiter ;;
CREATE TRIGGER `UpdateMaintainStatusToIdle` AFTER UPDATE ON `Device` FOR EACH ROW BEGIN
    IF OLD.Dstatus = '维修中' AND NEW.Dstatus = '空闲中' THEN
        UPDATE Maintain
        SET MdateEnd = NOW()
        WHERE MDid = OLD.Did
        ORDER BY Mid DESC
        LIMIT 1;
    END IF;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table Device
-- ----------------------------
DROP TRIGGER IF EXISTS `ReturnDevice`;
delimiter ;;
CREATE TRIGGER `ReturnDevice` AFTER UPDATE ON `Device` FOR EACH ROW BEGIN
    IF OLD.Dstatus = '使用中' AND NEW.Dstatus = '空闲中' THEN
        UPDATE BR
        SET BRrdate = NOW()
        WHERE BRDid = OLD.Did
				ORDER BY BRid DESC
        LIMIT 1;
    END IF;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table Device
-- ----------------------------
DROP TRIGGER IF EXISTS `UpdateDeviceStatusFix`;
delimiter ;;
CREATE TRIGGER `UpdateDeviceStatusFix` AFTER UPDATE ON `Device` FOR EACH ROW BEGIN
    IF OLD.Dstatus = '空闲中' AND NEW.Dstatus = '维修中' THEN
        INSERT INTO Maintain(MDid,MdateBegin) VALUES(NEW.Did,NOW());
    ELSEIF OLD.Dstatus = '使用中' AND NEW.Dstatus = '维修中' THEN
        INSERT INTO Maintain(MDid,MdateBegin) VALUES(NEW.Did,NOW());
        UPDATE BR SET BRrdate = NOW()
				ORDER BY BRid DESC
        LIMIT 1;
    END IF;
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
