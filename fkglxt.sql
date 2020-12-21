/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50727
Source Host           : localhost:3306
Source Database       : fkglxt

Target Server Type    : MYSQL
Target Server Version : 50727
File Encoding         : 65001

Date: 2020-09-24 16:34:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for datav
-- ----------------------------
DROP TABLE IF EXISTS `datav`;
CREATE TABLE `datav` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `zrfknum` int(11) NOT NULL COMMENT '昨日访客数',
  `nbunum` int(11) NOT NULL COMMENT '内部用户数',
  `wbunum` int(11) NOT NULL COMMENT '外部用户数',
  `servernum` int(11) NOT NULL COMMENT '平台提供服务总次数',
  `tjrq` date NOT NULL COMMENT '统计日期',
  `closeflownum` int(11) NOT NULL COMMENT '昨日完成访客流程',
  `dffknum` int(11) NOT NULL,
  `wdfknum` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for visitor
-- ----------------------------
DROP TABLE IF EXISTS `visitor`;
CREATE TABLE `visitor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `lfrq` date DEFAULT NULL COMMENT '来访日期',
  `jcsj` varchar(255) DEFAULT NULL COMMENT '进厂时间',
  `djy` varchar(255) DEFAULT NULL COMMENT '登记员',
  `khdw` varchar(255) DEFAULT NULL COMMENT '客户单位',
  `cph` varchar(255) DEFAULT NULL COMMENT '车牌号',
  `khxm` varchar(255) DEFAULT NULL COMMENT '客户姓名',
  `rcsy` varchar(255) DEFAULT NULL COMMENT '入场事由',
  `aqcs` varchar(255) DEFAULT NULL COMMENT '安全措施',
  `zkwp` varchar(255) DEFAULT NULL COMMENT '暂扣物品',
  `cwgbh` varchar(255) DEFAULT NULL COMMENT '储物柜编号',
  `sjh` varchar(255) DEFAULT NULL COMMENT '手机号',
  `jqbm` varchar(255) DEFAULT NULL COMMENT '接洽部门',
  `jqr` varchar(255) DEFAULT NULL COMMENT '接洽人',
  `ccsj` datetime DEFAULT NULL COMMENT '出厂时间',
  `lfryvxid` int(11) DEFAULT NULL COMMENT '来访人员微信用户id',
  `sfqylf` int(2) DEFAULT '0' COMMENT '是否确认来访（0未确认，1已确认，2已拒绝）',
  `qrrvxid` int(11) DEFAULT NULL COMMENT '确认人微信用户id',
  `sfjslc` int(2) DEFAULT '0' COMMENT '是否结束流程(0未结束，1已结束)',
  `idcard` varchar(255) DEFAULT NULL COMMENT '身份证号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `openid` varchar(255) DEFAULT NULL COMMENT '微信openid',
  `code` int(2) DEFAULT '0' COMMENT '是否为内部人员（0不是，1是）',
  `realname` varchar(255) DEFAULT NULL COMMENT '真实姓名',
  `zcsj` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '注册时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=88 DEFAULT CHARSET=utf8;
