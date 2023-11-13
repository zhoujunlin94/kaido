CREATE DATABASE IF NOT EXISTS `base` CHARACTER SET 'utf8mb4';
USE `base`;

-- 系统缓存配置表
drop table if exists `sys_cache_config`;
CREATE TABLE `sys_cache_config`
(
    `id`         int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `key`        varchar(255)     NOT NULL DEFAULT '' COMMENT '配置key',
    `value`      varchar(2048)    NOT NULL DEFAULT '' COMMENT '配置值',
    `desc`       varchar(1024)    NOT NULL DEFAULT '' COMMENT '配置描述',

    `is_delete`  tinyint(1)       NOT NULL DEFAULT '0' COMMENT '逻辑删除标志位',
    `created_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '创建人',
    `created_at` datetime         NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '更新人',
    `updated_at` datetime         NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uniq_key` (`key`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  ROW_FORMAT = DYNAMIC COMMENT ='系统缓存配置表';

-- 系统资源表
drop table if exists `sys_resource`;
CREATE TABLE `sys_resource`
(
    `id`              int(11) UNSIGNED    NOT NULL AUTO_INCREMENT COMMENT '主键  权限id',
    `resource_code`   varchar(64)         NOT NULL DEFAULT '' COMMENT '权限code',
    `resource_name`   varchar(255)        NOT NULL DEFAULT '' COMMENT '权限名',
    `resource_type`   varchar(32)         NOT NULL DEFAULT 'MENU' COMMENT '资源类型 菜单、按钮',
    `resource_parent` int(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '父级资源id',
    `resource_status` tinyint(1)          NOT NULL DEFAULT 0 COMMENT '是否有效（0-无效；1-有效）',
    `resource_icon`   varchar(32)         NOT NULL DEFAULT '' COMMENT '资源图标',
    `resource_path`   varchar(64)         NOT NULL DEFAULT '' COMMENT '资源路径',
    `resource_order`  int(11)             NOT NULL DEFAULT 0 COMMENT '资源排序',

    `is_deleted`      tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 1已删除 0正常数据',
    `created_by`      int(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '创建用户',
    `created_at`      datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_by`      int(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '更新人',
    `updated_at`      datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    INDEX `idx_resource_parent` (`resource_parent`) USING BTREE,
    UNIQUE INDEX `uniq_permission` (`resource_code`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  ROW_FORMAT = DYNAMIC COMMENT ='系统资源表';

-- 系统角色
drop table if exists `sys_role`;
CREATE TABLE `sys_role`
(
    `id`          int(11) UNSIGNED    NOT NULL AUTO_INCREMENT COMMENT '主键  角色id',
    `role_code`   varchar(32)         NOT NULL DEFAULT '' COMMENT '角色code',
    `role_name`   varchar(255)        NOT NULL DEFAULT '' COMMENT '角色名',
    `role_status` tinyint(1)          NOT NULL DEFAULT 0 COMMENT '是否有效（0-无效；1-有效）',

    `is_deleted`  tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 1已删除 0正常数据',
    `created_by`  int(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '创建用户',
    `created_at`  datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_by`  int(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '更新人',
    `updated_at`  datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE INDEX `uniq_role` (`role_code`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  ROW_FORMAT = DYNAMIC COMMENT ='系统角色表';

-- 系统角色资源关联表
drop table if exists `sys_role_resource`;
CREATE TABLE `sys_role_resource`
(
    `id`          int(11) UNSIGNED    NOT NULL AUTO_INCREMENT COMMENT '主键',
    `role_id`     int(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '角色id',
    `resource_id` int(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '权限id',

    `is_deleted`  tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 1已删除 0正常数据',
    `created_by`  int(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '创建用户',
    `created_at`  datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_by`  int(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '更新人',
    `updated_at`  datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE INDEX `uniq_role_permission` (`role_id`, `resource_id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  ROW_FORMAT = DYNAMIC COMMENT ='系统角色资源表';

-- 系统用户表
drop table if exists `sys_user`;
CREATE TABLE `sys_user`
(
    `id`            int(11) UNSIGNED    NOT NULL AUTO_INCREMENT COMMENT '主键  用户id',
    `account_name`  varchar(32)         NOT NULL DEFAULT '' COMMENT '账户',
    `user_name`     varchar(32)         NOT NULL DEFAULT '' COMMENT '用户真实姓名',
    `user_password` varchar(255)        NOT NULL DEFAULT '' COMMENT '密码',
    `user_status`   tinyint(1)          NOT NULL DEFAULT 0 COMMENT '是否有效（0-无效；1-有效）',

    `is_deleted`    tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 1已删除 0正常数据',
    `created_by`    int(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '创建用户',
    `created_at`    datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_by`    int(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '更新人',
    `updated_at`    datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE INDEX `uniq_account` (`account_name`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  ROW_FORMAT = DYNAMIC COMMENT ='系统用户表';

-- 系统用户角色表关联
drop table if exists `sys_user_role`;
CREATE TABLE `sys_user_role`
(
    `id`         int(11) UNSIGNED    NOT NULL AUTO_INCREMENT COMMENT '主键',
    `user_id`    int(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '用户id',
    `role_id`    int(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '角色id',

    `is_deleted` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 1已删除 0正常数据',
    `created_by` int(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '创建用户',
    `created_at` datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_by` int(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '更新人',
    `updated_at` datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE INDEX `uniq_user_role` (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  ROW_FORMAT = DYNAMIC COMMENT ='系统用户角色表';

