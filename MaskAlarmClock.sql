-- --------------------------------------------------------
-- 主机:                           localhost
-- 服务器版本:                        8.0.12 - MySQL Community Server - GPL
-- 服务器操作系统:                      Linux
-- HeidiSQL 版本:                  9.5.0.5256
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- 导出 mask_alarm_clock 的数据库结构
CREATE DATABASE IF NOT EXISTS `mask_alarm_clock` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `mask_alarm_clock`;

-- 导出  表 mask_alarm_clock.alarm_clock 结构
CREATE TABLE IF NOT EXISTS `alarm_clock` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name_ch` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '中文名称',
  `name_en` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '英文名称',
  `icon` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '图片',
  `background` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '背景图',
  `music` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '音乐',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='闹钟';

-- 数据导出被取消选择。
-- 导出  表 mask_alarm_clock.comment 结构
CREATE TABLE IF NOT EXISTS `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `picture` varchar(255) DEFAULT NULL COMMENT '图片',
  `count` int(11) DEFAULT NULL COMMENT '评论条数',
  `star` int(11) DEFAULT '0' COMMENT '星值:0-10',
  `data` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '内容',
  `created_time` timestamp NOT NULL COMMENT '创建时间',
  `updated_time` timestamp NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='点评';

-- 数据导出被取消选择。
-- 导出  表 mask_alarm_clock.discuss 结构
CREATE TABLE IF NOT EXISTS `discuss` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `comment_id` int(11) NOT NULL COMMENT '点评ID',
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `icon` varchar(50) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL COMMENT '用户昵称',
  `star` int(11) NOT NULL DEFAULT '0' COMMENT '星值',
  `remark` varchar(255) DEFAULT NULL COMMENT '评语',
  `created_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='评语表';

-- 数据导出被取消选择。
-- 导出  表 mask_alarm_clock.face_score 结构
CREATE TABLE IF NOT EXISTS `face_score` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `style` int(11) DEFAULT NULL COMMENT '变动类型：1补水，2美白，3紧致，4滋润，5听音乐，6点评',
  `num` int(11) DEFAULT NULL COMMENT '数量',
  `type` int(11) DEFAULT NULL COMMENT '得失分：1:，得分，2，失分；',
  `score` int(11) DEFAULT NULL COMMENT '得分，可以为负数',
  `created_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=utf8 COMMENT='颜值列表';

-- 数据导出被取消选择。
-- 导出  表 mask_alarm_clock.user 结构
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '昵称',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '头像',
  `sex` int(11) NOT NULL COMMENT '性别：1男性，2，女性',
  `point` int(11) DEFAULT '0' COMMENT '颜值',
  `phone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '手机号',
  `check_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '验证码',
  `uuid` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '第三方登录ID',
  `created_time` timestamp NOT NULL,
  `updated_time` timestamp NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- 数据导出被取消选择。
-- 导出  表 mask_alarm_clock.user_collection 结构
CREATE TABLE IF NOT EXISTS `user_collection` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `alarm_clock_id` int(11) NOT NULL COMMENT '闹钟ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户收藏表';

-- 数据导出被取消选择。
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
