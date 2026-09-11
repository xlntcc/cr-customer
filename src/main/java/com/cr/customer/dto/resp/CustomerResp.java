package com.cr.customer.dto.resp;

import lombok.Data;

import java.util.List;

/**
 * 客户详情响应
 */
@Data
public class CustomerResp {

    /** 客户ID */
    private String customerId;

    /** 客户编号 */
    private String customerNo;

    /** 客户类型：个人/企业 */
    private String customerType;

    /** 客户名称 */
    private String name;

    /** 证件类型：身份证/营业执照 */
    private String idType;

    /** 证件号码(脱敏) */
    private String idNumber;

    /** 手机号(脱敏) */
    private String phone;

    /** 邮箱 */
    private String email;

    /** 联系地址 */
    private String address;

    /** 风险等级：低/中/高 */
    private String riskLevel;

    /** 信用评分 */
    private Integer creditScore;

    /** 客户标签列表 */
    private List<String> tags;

    /** 来源渠道 */
    private String sourceChannel;

    /** 创建时间 */
    private String createdAt;

    /** 更新时间 */
    private String updatedAt;
}