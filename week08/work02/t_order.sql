CREATE TABLE `t_order` (
  `order_id` bigint(20) NOT NULL COMMENT '主键，订单id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '数据更新时间',
  `is_delete` int(11) DEFAULT '0' COMMENT '是否删除：1删除',
  `user_id` bigint(20) NOT NULL COMMENT '关联的用户id',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单表';
