CREATE DATABASE IF NOT EXISTS car_rental DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE car_rental;

-- 客户表
CREATE TABLE IF NOT EXISTS t_customer (
    customer_id    BIGINT       NOT NULL AUTO_INCREMENT COMMENT '客户ID',
    customer_no    VARCHAR(32)  NOT NULL COMMENT '客户编号 全局唯一',
    customer_type  TINYINT      NOT NULL COMMENT '客户类型 1-个人 2-企业',
    name           VARCHAR(128) NOT NULL COMMENT '姓名/企业名称',
    id_type        TINYINT      NOT NULL COMMENT '证件类型 1-身份证 2-营业执照 3-其他',
    id_no          VARCHAR(64)  DEFAULT NULL COMMENT '证件号码(AES加密存储)',
    phone          VARCHAR(20)  DEFAULT NULL COMMENT '手机号(AES加密存储)',
    email          VARCHAR(128) DEFAULT NULL COMMENT '邮箱',
    address        VARCHAR(256) DEFAULT NULL COMMENT '联系地址',
    risk_level     TINYINT      DEFAULT 2 COMMENT '风险等级 1-低 2-中 3-高',
    credit_score   INT          DEFAULT 0 COMMENT '信用评分',
    tags           VARCHAR(512) DEFAULT '[]' COMMENT '标签列表(JSON数组)',
    source_channel VARCHAR(64)  DEFAULT NULL COMMENT '来源渠道',
    created_time   DATETIME     DEFAULT NULL COMMENT '创建时间',
    updated_time   DATETIME     DEFAULT NULL COMMENT '更新时间',
    deleted        TINYINT      DEFAULT 0 COMMENT '逻辑删除 0-未删除 1-已删除',
    PRIMARY KEY (customer_id),
    UNIQUE KEY uk_customer_no (customer_no),
    KEY idx_name (name),
    KEY idx_phone (phone),
    KEY idx_customer_type (customer_type)
) ENGINE = InnoDB COMMENT = '客户表';

-- 客户认证表(KYC)
CREATE TABLE IF NOT EXISTS t_customer_auth (
    auth_id        BIGINT      NOT NULL AUTO_INCREMENT COMMENT '认证ID',
    customer_id    BIGINT      NOT NULL COMMENT '客户ID',
    auth_type      TINYINT     NOT NULL COMMENT '认证类型 1-身份证 2-人脸 3-企业工商',
    auth_status    TINYINT     NOT NULL DEFAULT 0 COMMENT '认证状态 0-未认证 1-已认证 2-认证失败',
    auth_time      DATETIME    DEFAULT NULL COMMENT '认证时间',
    auth_result    VARCHAR(512) DEFAULT NULL COMMENT '认证结果',
    auth_channel   VARCHAR(64) DEFAULT NULL COMMENT '认证渠道',
    PRIMARY KEY (auth_id),
    KEY idx_customer_id (customer_id)
) ENGINE = InnoDB COMMENT = '客户认证表';

-- 客户联系人表
CREATE TABLE IF NOT EXISTS t_customer_contact (
    contact_id     BIGINT      NOT NULL AUTO_INCREMENT COMMENT '联系人ID',
    customer_id    BIGINT      NOT NULL COMMENT '客户ID',
    contact_name   VARCHAR(64) NOT NULL COMMENT '联系人姓名',
    relation       VARCHAR(32) DEFAULT NULL COMMENT '与客户关系',
    contact_phone  VARCHAR(20) DEFAULT NULL COMMENT '联系电话',
    priority       TINYINT     DEFAULT 1 COMMENT '优先级 1-高 2-中 3-低',
    PRIMARY KEY (contact_id),
    KEY idx_customer_id (customer_id)
) ENGINE = InnoDB COMMENT = '客户联系人表';

-- 客户标签表
CREATE TABLE IF NOT EXISTS t_customer_tag (
    tag_id       BIGINT       NOT NULL AUTO_INCREMENT COMMENT '标签ID',
    customer_id  BIGINT       NOT NULL COMMENT '客户ID',
    tag_name     VARCHAR(64)  NOT NULL COMMENT '标签名',
    tag_value    VARCHAR(255) DEFAULT NULL COMMENT '标签值',
    tag_source   TINYINT      DEFAULT 0 COMMENT '标签来源 0-手动 1-AI',
    PRIMARY KEY (tag_id),
    KEY idx_customer_id (customer_id)
) ENGINE = InnoDB COMMENT = '客户标签表';

-- 客户渠道表
CREATE TABLE IF NOT EXISTS t_customer_channel (
    channel_id      BIGINT        NOT NULL AUTO_INCREMENT COMMENT '渠道ID',
    channel_name    VARCHAR(64)   NOT NULL COMMENT '渠道名称',
    channel_code    VARCHAR(32)   NOT NULL COMMENT '渠道编码',
    contact_person  VARCHAR(64)   DEFAULT NULL COMMENT '联系人',
    contact_phone   VARCHAR(20)   DEFAULT NULL COMMENT '联系电话',
    commission_rate DECIMAL(8, 4) DEFAULT NULL COMMENT '佣金比例',
    created_time    DATETIME      DEFAULT NULL COMMENT '创建时间',
    updated_time    DATETIME      DEFAULT NULL COMMENT '更新时间',
    deleted         TINYINT       DEFAULT 0 COMMENT '逻辑删除 0-未删除 1-已删除',
    PRIMARY KEY (channel_id),
    UNIQUE KEY uk_channel_code (channel_code)
) ENGINE = InnoDB COMMENT = '客户渠道表';