/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50720
Source Host           : 127.0.0.1:3306
Source Database       : geekhomeworkslave

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2021-12-22 23:56:30
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for  t_order_commodity_raletion
-- ----------------------------
DROP TABLE IF EXISTS ` t_order_commodity_raletion`;
CREATE TABLE ` t_order_commodity_raletion` (
  `row_guid` varchar(50) NOT NULL COMMENT '主键，UUID生成',
  `order_guid` varchar(50) NOT NULL COMMENT '订单guid',
  `commodity_guid` varchar(50) NOT NULL COMMENT '商品guid',
  `buy_number` int(11) NOT NULL COMMENT '购买数量',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '数据更新时间',
  PRIMARY KEY (`row_guid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单-商品关联表';

-- ----------------------------
-- Records of  t_order_commodity_raletion
-- ----------------------------

-- ----------------------------
-- Table structure for t_commodity
-- ----------------------------
DROP TABLE IF EXISTS `t_commodity`;
CREATE TABLE `t_commodity` (
  `commodity_guid` varchar(50) NOT NULL COMMENT '主键，商品guid',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '数据更新时间',
  `is_enable` int(11) unsigned DEFAULT '0' COMMENT '是否启用：1启用 0禁用',
  `commodity_name` varchar(50) NOT NULL COMMENT '商品名称',
  `commodity_price` decimal(10,2) NOT NULL COMMENT '商品价格',
  `commodity_descript` varchar(200) DEFAULT NULL COMMENT '商品简介',
  `commodity_stock` bigint(20) NOT NULL COMMENT '商品存量',
  PRIMARY KEY (`commodity_guid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品表';

-- ----------------------------
-- Records of t_commodity
-- ----------------------------

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `order_guid` varchar(50) NOT NULL COMMENT '主键，订单guid',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '数据更新时间',
  `is_delete` int(11) DEFAULT '0' COMMENT '是否删除：1删除',
  `user_guid` varchar(50) NOT NULL COMMENT '关联的用户guid',
  PRIMARY KEY (`order_guid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单表';

-- ----------------------------
-- Records of t_order
-- ----------------------------

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `user_guid` varchar(50) NOT NULL COMMENT '主键，用户guid',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '数据更新时间',
  `is_enable` int(11) DEFAULT '1' COMMENT '是否启用：1启用 0禁用',
  `login_id` varchar(50) NOT NULL COMMENT '登录账号',
  `password` varchar(50) NOT NULL COMMENT '用户登录密码',
  `user_name` varchar(50) NOT NULL COMMENT '用户名称',
  `user_type` varchar(50) DEFAULT NULL COMMENT '用户类型',
  `sex` varchar(50) DEFAULT NULL COMMENT '性别',
  `phone` varchar(50) DEFAULT NULL COMMENT '联系电话',
  `address` varchar(500) DEFAULT NULL COMMENT '收货地址',
  PRIMARY KEY (`user_guid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('5', '2021-12-14 22:26:40', '2021-12-14 22:26:44', '1', 'jjh', 'uu', 'user_slave', 'u', '1', '1', '1');
