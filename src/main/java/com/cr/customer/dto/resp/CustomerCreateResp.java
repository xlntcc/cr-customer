package com.cr.customer.dto.resp;

import lombok.Data;

/**
 * 创建客户响应
 */
@Data
public class CustomerCreateResp {

    private String customerId;

    private String customerNo;

    private String createdAt;
}