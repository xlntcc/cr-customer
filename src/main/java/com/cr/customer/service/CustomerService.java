package com.cr.customer.service;

import com.cr.commons.result.PageResult;
import com.cr.customer.dto.req.ChannelCreateReq;
import com.cr.customer.dto.req.CustomerCreateReq;
import com.cr.customer.dto.req.CustomerQueryReq;
import com.cr.customer.dto.req.CustomerTagReq;
import com.cr.customer.dto.req.CustomerUpdateReq;
import com.cr.customer.dto.resp.ChannelCreateResp;
import com.cr.customer.dto.resp.CustomerCreateResp;
import com.cr.customer.dto.resp.CustomerProfileResp;
import com.cr.customer.dto.resp.CustomerResp;
import com.cr.customer.dto.resp.CustomerUpdateResp;

/**
 * 客户服务
 */
public interface CustomerService {

    /** 创建客户 */
    CustomerCreateResp createCustomer(CustomerCreateReq req);

    /** 按客户ID查询客户详情 */
    CustomerResp getCustomer(Long customerId);

    /** 分页查询客户列表 */
    PageResult<CustomerResp> listCustomers(CustomerQueryReq req);

    /** 更新客户信息 */
    CustomerUpdateResp updateCustomer(Long customerId, CustomerUpdateReq req);

    /** 查询客户360画像 */
    CustomerProfileResp getProfile(Long customerId);

    /** 为客户添加标签 */
    void addTags(Long customerId, CustomerTagReq req);

    /** 创建/更新渠道信息 */
    ChannelCreateResp saveChannel(ChannelCreateReq req);
}