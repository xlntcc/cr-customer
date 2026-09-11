package com.cr.customer.dto.resp;

import lombok.Data;

import java.util.List;

/**
 * 客户详情响应
 */
@Data
public class CustomerResp {

    private String customerId;

    private String customerNo;

    private String customerType;

    private String name;

    private String idType;

    private String idNumber;

    private String phone;

    private String email;

    private String address;

    private String riskLevel;

    private Integer creditScore;

    private List<String> tags;

    private String sourceChannel;

    private String createdAt;

    private String updatedAt;
}