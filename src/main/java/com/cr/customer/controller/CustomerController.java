package com.cr.customer.controller;

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
import com.cr.customer.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 客户中心接口
 */
@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public Result<CustomerCreateResp> createCustomer(@Valid @RequestBody CustomerCreateReq req) {
        return Result.success(customerService.createCustomer(req));
    }

    @GetMapping("/{customerId}")
    public Result<CustomerResp> getCustomer(@PathVariable Long customerId) {
        return Result.success(customerService.getCustomer(customerId));
    }

    @PostMapping("/list")
    public Result<PageResult<CustomerResp>> listCustomers(@RequestBody CustomerQueryReq req) {
        return Result.success(customerService.listCustomers(req));
    }

    @PutMapping("/{customerId}")
    public Result<CustomerUpdateResp> updateCustomer(@PathVariable Long customerId,
                                                     @Valid @RequestBody CustomerUpdateReq req) {
        return Result.success(customerService.updateCustomer(customerId, req));
    }

    @GetMapping("/{customerId}/profile")
    public Result<CustomerProfileResp> getProfile(@PathVariable Long customerId) {
        return Result.success(customerService.getProfile(customerId));
    }

    @PostMapping("/{customerId}/tags")
    public Result<Void> addTags(@PathVariable Long customerId, @Valid @RequestBody CustomerTagReq req) {
        customerService.addTags(customerId, req);
        return Result.success();
    }

    @PostMapping("/channels")
    public Result<ChannelCreateResp> saveChannel(@Valid @RequestBody ChannelCreateReq req) {
        return Result.success(customerService.saveChannel(req));
    }
}