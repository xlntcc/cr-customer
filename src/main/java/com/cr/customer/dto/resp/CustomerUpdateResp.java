package com.cr.customer.dto.resp;

import lombok.Data;

/**
 * 更新客户响应
 */
@Data
public class CustomerUpdateResp {

    /** 客户ID */
    private String customerId;

    /** 客户编号 */
    private String customerNo;

    /** 更新时间 */
    private String updatedAt;
}