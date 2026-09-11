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

    CustomerCreateResp createCustomer(CustomerCreateReq req);

    CustomerResp getCustomer(Long customerId);

    PageResult<CustomerResp> listCustomers(CustomerQueryReq req);

    CustomerUpdateResp updateCustomer(Long customerId, CustomerUpdateReq req);

    CustomerProfileResp getProfile(Long customerId);

    void addTags(Long customerId, CustomerTagReq req);

    ChannelCreateResp saveChannel(ChannelCreateReq req);
}