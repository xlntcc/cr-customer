package com.cr.customer.dto.req;

import lombok.Data;

import java.util.List;

/**
 * 更新客户请求
 */
@Data
public class CustomerUpdateReq {

    /** 客户名称 */
    private String name;

    /** 邮箱 */
    private String email;

    /** 联系地址 */
    private String address;

    /** 客户标签列表 */
    private List<String> tags;

    /** 来源渠道 */
    private String sourceChannel;
}