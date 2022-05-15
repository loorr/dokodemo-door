CREATE DATABASE IF NOT EXISTS `config_center` DEFAULT CHARACTER SET utf8mb4 DEFAULT COLLATE utf8mb4_general_ci;

CREATE  TABLE IF NOT EXISTS `config_center`.`config` (
    `id` BIGINT(20) UNSIGNED auto_increment COMMENT '主键',
    `key` VARCHAR(63) unique NOT NULL COMMENT '配置Key',
    `secret_key` VARCHAR(255) default NULL COMMENT 'secret key',
    `describe` VARCHAR(255) default NULL COMMENT '描述',
    `content` text default NULL COMMENT 'data',
    `state` tinyint(3) default 0 COMMENT '状态 0 生效中; 1 暂停; 3 已删除',
    `db_create_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `db_modify_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY(`id`)
) ENGINE=InnoDB COMMENT = '应用信息表';

CREATE  TABLE IF NOT EXISTS `config_center`.`config_log` (
    `id` BIGINT(20) UNSIGNED auto_increment COMMENT '主键',
    `key` VARCHAR(63) NOT NULL COMMENT '配置Key',
    `update_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    `secret_key` VARCHAR(255) default NULL COMMENT 'secret key',
    `describe` VARCHAR(255) default NULL COMMENT '描述',
    `content` text default NULL COMMENT 'data',
    unique INDEX `uniq_key_time` (`key`, `update_time`) USING BTREE,
    PRIMARY KEY(`id`)
) ENGINE=InnoDB COMMENT = '应用信息表历史';