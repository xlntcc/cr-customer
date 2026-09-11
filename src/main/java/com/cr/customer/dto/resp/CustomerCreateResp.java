package com.cr.customer.dto.resp;

import lombok.Data;

/**
 * 创建客户响应
 */
@Data
public class CustomerCreateResp {

    /** 客户ID */
    private String customerId;

    /** 客户编号 */
    private String customerNo;

    /** 创建时间 */
    private String createdAt;
}