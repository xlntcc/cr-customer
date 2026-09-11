package com.cr.customer.api;

import com.cr.commons.result.PageResult;
import com.cr.commons.result.Result;
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
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 客户中心对外Feign接口，供order-service、contract-service等下游服务调用
 */
@FeignClient(name = "customer-service", contextId = "customerApi")
public interface CustomerApi {

    /** 创建客户 */
    @PostMapping("/api/v1/customers")
    Result<CustomerCreateResp> createCustomer(@Valid @RequestBody CustomerCreateReq req);

    /** 查询客户详情 */
    @GetMapping("/api/v1/customers/{customerId}")
    Result<CustomerResp> getCustomer(@PathVariable Long customerId);

    /** 分页查询客户列表 */
    @PostMapping("/api/v1/customers/list")
    Result<PageResult<CustomerResp>> listCustomers(@RequestBody CustomerQueryReq req);

    /** 更新客户信息 */
    @PutMapping("/api/v1/customers/{customerId}")
    Result<CustomerUpdateResp> updateCustomer(@PathVariable Long customerId,
                                              @Valid @RequestBody CustomerUpdateReq req);

    /** 查询客户360画像 */
    @GetMapping("/api/v1/customers/{customerId}/profile")
    Result<CustomerProfileResp> getProfile(@PathVariable Long customerId);

    /** 为客户添加标签 */
    @PostMapping("/api/v1/customers/{customerId}/tags")
    Result<Void> addTags(@PathVariable Long customerId, @Valid @RequestBody CustomerTagReq req);

    /** 创建/更新渠道信息 */
    @PostMapping("/api/v1/customers/channels")
    Result<ChannelCreateResp> saveChannel(@Valid @RequestBody ChannelCreateReq req);
}