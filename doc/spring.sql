# 测试用户表
CREATE TABLE `test_user`
(
    `id`             bigint       NOT NULL AUTO_INCREMENT COMMENT '主键',
    `account`        varchar(40)  NOT NULL COMMENT '账户',
    `nickname`       varchar(40)  NOT NULL COMMENT '昵称',
    `password`       varchar(255) NOT NULL COMMENT '密码',
    `create_time`    datetime     NOT NULL COMMENT '创建时间',
    `update_time`    datetime DEFAULT NULL COMMENT '更新时间',
    `account_status` int          NOT NULL COMMENT '账户状态 0正常 1冻结',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8 COMMENT ='用户表';