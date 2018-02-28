
CREATE DATABASE IF NOT EXISTS `hezizhe721` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci ;

USE `hezizhe721`;


DROP TABLE IF EXISTS `piece`;

CREATE TABLE `piece` (
  `id` int(2) unsigned NOT NULL AUTO_INCREMENT,
  `power` int(2) unsigned NOT NULL,
  `locationX` int(2) NOT NULL DEFAULT '0',
  `locationY` int(2) NOT NULL DEFAULT '0',
  `hide` int(2) NOT NULL DEFAULT '0' COMMENT '1为显示 0为隐藏',
  `camp` int(2) NOT NULL COMMENT '1为蓝方 0为红方',
  `round` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `player`;

CREATE TABLE `player` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `playerName` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `camp` int(2) NOT NULL DEFAULT '-1' COMMENT '1为蓝方 0为红方',
  `round` bigint(20) NOT NULL DEFAULT '0',
  `overTimes` int(2) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8;

insert  into `player`(`playerName`,`password`) values ('admin','admin'),('123','123'),('111','111');

DROP TABLE IF EXISTS `round`;

CREATE TABLE `round` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `winner` int(2),
  `first` int(2) NOT NULL DEFAULT '-1' COMMENT '先手方 1为蓝方 0为红方',
  `steps` int(10) NOT NULL DEFAULT '0' COMMENT '回合数',
  `peaceSteps` int(10) NOT NULL DEFAULT '0' COMMENT '停战回合数',
  `status` int(2) NOT NULL DEFAULT '0' COMMENT '0为准备，1为对战中，2为结束',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

insert  into `round`(`peaceSteps`) values (0),(0),(0);


