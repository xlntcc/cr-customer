package com.cr.customer.dto.req;

import lombok.Data;

import java.util.List;

/**
 * 更新客户请求
 */
@Data
public class CustomerUpdateReq {

    private String name;

    private String email;

    private String address;

    private List<String> tags;

    private String sourceChannel;
}