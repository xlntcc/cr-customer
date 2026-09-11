package com.cr.customer.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 客户实体
 */
@Data
@TableName("t_customer")
public class Customer {

    /** 客户ID */
    @TableId(value = "customer_id", type = IdType.AUTO)
    private Long customerId;

    /** 客户编号，全局唯一 */
    private String customerNo;

    /** 客户类型 1-个人 2-企业 */
    private Integer customerType;

    /** 姓名/企业名称 */
    private String name;

    /** 证件类型 1-身份证 2-营业执照 3-其他 */
    private Integer idType;

    /** 证件号码(AES加密存储) */
    private String idNo;

    /** 手机号(AES加密存储) */
    private String phone;

    /** 邮箱 */
    private String email;

    /** 联系地址 */
    private String address;

    /** 风险等级 1-低 2-中 3-高 */
    private Integer riskLevel;

    /** 信用评分 */
    private Integer creditScore;

    /** 标签列表(JSON数组) */
    private String tags;

    /** 来源渠道 */
    private String sourceChannel;

    /** 创建时间 */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdTime;

    /** 更新时间 */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedTime;

    /** 逻辑删除标识 0-未删除 1-已删除 */
    @TableLogic
    private Integer deleted;
}