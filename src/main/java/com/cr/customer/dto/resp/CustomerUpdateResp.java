package com.cr.customer.dto.resp;

import lombok.Data;

/**
 * 更新客户响应
 */
@Data
public class CustomerUpdateResp {

    private String customerId;

    private String customerNo;

    private String updatedAt;
}