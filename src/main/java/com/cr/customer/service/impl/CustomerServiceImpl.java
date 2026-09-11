package com.cr.customer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cr.commons.enums.CustomerTypeEnum;
import com.cr.commons.enums.IdTypeEnum;
import com.cr.commons.enums.RiskLevelEnum;
import com.cr.commons.exception.BizException;
import com.cr.commons.result.PageResult;
import com.cr.commons.result.ResultCode;
import com.cr.commons.util.DateUtils;
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
import com.cr.customer.entity.Customer;
import com.cr.customer.entity.CustomerChannel;
import com.cr.customer.mapper.CustomerChannelMapper;
import com.cr.customer.mapper.CustomerMapper;
import com.cr.customer.service.CustomerService;
import com.cr.customer.support.CustomerConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

/**
 * 客户服务实现
 */
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    /** 客户编号时间格式 */
    private static final DateTimeFormatter NO_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    private final CustomerMapper customerMapper;

    private final CustomerChannelMapper customerChannelMapper;

    private final CustomerConverter converter;

    /** 创建客户 */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public CustomerCreateResp createCustomer(CustomerCreateReq req) {
        CustomerTypeEnum customerType = CustomerTypeEnum.of(req.getCustomerType());
        if (customerType == null) {
            throw new BizException(ResultCode.BAD_REQUEST, "无效的客户类型");
        }
        IdTypeEnum idType = IdTypeEnum.of(req.getIdType());
        if (idType == null) {
            throw new BizException(ResultCode.BAD_REQUEST, "无效的证件类型");
        }

        Customer customer = new Customer();
        customer.setCustomerType(customerType.getCode());
        customer.setName(req.getName());
        customer.setIdType(idType.getCode());
        customer.setIdNo(req.getIdNumber());
        customer.setPhone(req.getPhone());
        customer.setEmail(req.getEmail());
        customer.setAddress(req.getAddress());
        customer.setTags(converter.writeTags(req.getTags()));
        customer.setSourceChannel(req.getSourceChannel());
        customer.setRiskLevel(RiskLevelEnum.MEDIUM.getCode());
        customer.setCreditScore(0);
        customer.setCustomerNo(generateCustomerNo());
        customerMapper.insert(customer);

        CustomerCreateResp resp = new CustomerCreateResp();
        resp.setCustomerId(String.valueOf(customer.getCustomerId()));
        resp.setCustomerNo(customer.getCustomerNo());
        resp.setCreatedAt(DateUtils.format(customer.getCreatedTime()));
        return resp;
    }

    /** 按客户ID查询客户详情 */
    @Override
    public CustomerResp getCustomer(Long customerId) {
        return converter.toResp(getByIdOrThrow(customerId));
    }

    /** 分页查询客户列表 */
    @Override
    public PageResult<CustomerResp> listCustomers(CustomerQueryReq req) {
        LambdaQueryWrapper<Customer> wrapper = new LambdaQueryWrapper<>();
        CustomerTypeEnum customerType = CustomerTypeEnum.of(req.getCustomerType());
        if (customerType != null) {
            wrapper.eq(Customer::getCustomerType, customerType.getCode());
        }
        RiskLevelEnum riskLevel = RiskLevelEnum.of(req.getRiskLevel());
        if (riskLevel != null) {
            wrapper.eq(Customer::getRiskLevel, riskLevel.getCode());
        }
        if (StringUtils.hasText(req.getKeyword())) {
            String keyword = req.getKeyword();
            wrapper.and(w -> w.like(Customer::getName, keyword)
                    .or().like(Customer::getPhone, keyword)
                    .or().like(Customer::getIdNo, keyword));
        }
        wrapper.orderByDesc(Customer::getCreatedTime);

        Page<Customer> page = customerMapper.selectPage(new Page<>(req.getPageNum(), req.getPageSize()), wrapper);
        List<CustomerResp> list = page.getRecords().stream()
                .map(converter::toResp)
                .collect(Collectors.toList());
        return PageResult.of(list, page.getTotal(), req.getPageNum(), req.getPageSize());
    }

    /** 更新客户信息 */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public CustomerUpdateResp updateCustomer(Long customerId, CustomerUpdateReq req) {
        Customer customer = getByIdOrThrow(customerId);
        customer.setName(req.getName());
        customer.setEmail(req.getEmail());
        customer.setAddress(req.getAddress());
        customer.setTags(converter.writeTags(req.getTags()));
        customer.setSourceChannel(req.getSourceChannel());
        customerMapper.updateById(customer);

        CustomerUpdateResp resp = new CustomerUpdateResp();
        resp.setCustomerId(String.valueOf(customer.getCustomerId()));
        resp.setCustomerNo(customer.getCustomerNo());
        resp.setUpdatedAt(DateUtils.format(customer.getUpdatedTime()));
        return resp;
    }

    /** 查询客户360画像 */
    @Override
    public CustomerProfileResp getProfile(Long customerId) {
        Customer customer = getByIdOrThrow(customerId);
        CustomerResp detail = converter.toResp(customer);

        return CustomerProfileResp.builder()
                .basicInfo(CustomerProfileResp.BasicInfo.builder()
                        .customerId(detail.getCustomerId())
                        .customerNo(detail.getCustomerNo())
                        .customerType(detail.getCustomerType())
                        .name(detail.getName())
                        .idType(detail.getIdType())
                        .phone(detail.getPhone())
                        .email(detail.getEmail())
                        .address(detail.getAddress())
                        .riskLevel(detail.getRiskLevel())
                        .creditScore(detail.getCreditScore())
                        .sourceChannel(detail.getSourceChannel())
                        .createdAt(detail.getCreatedAt())
                        .build())
                .orderStats(CustomerProfileResp.OrderStats.builder()
                        .totalOrders(0L)
                        .activeOrders(0L)
                        .completedOrders(0L)
                        .overdueOrders(0L)
                        .build())
                .riskScore(CustomerProfileResp.RiskScore.builder()
                        .score(customer.getCreditScore() == null ? 0 : customer.getCreditScore())
                        .riskLevel(detail.getRiskLevel())
                        .suggestion("无")
                        .build())
                .assetInfo(CustomerProfileResp.AssetInfo.builder()
                        .vehicleCount(0L)
                        .mortgageStatus("无")
                        .build())
                .build();
    }

    /** 为客户添加标签 */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addTags(Long customerId, CustomerTagReq req) {
        Customer customer = getByIdOrThrow(customerId);
        converter.appendTags(customer, req.getTags());
        customerMapper.updateById(customer);
    }

    /** 创建/更新渠道信息，存在则更新，不存在则创建 */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ChannelCreateResp saveChannel(ChannelCreateReq req) {
        CustomerChannel channel = customerChannelMapper.selectOne(
                new LambdaQueryWrapper<CustomerChannel>().eq(CustomerChannel::getChannelCode, req.getChannelCode()));
        boolean isCreate = channel == null;
        if (isCreate) {
            channel = new CustomerChannel();
            channel.setChannelCode(req.getChannelCode());
        }
        channel.setChannelName(req.getChannelName());
        channel.setContactPerson(req.getContactPerson());
        channel.setContactPhone(req.getContactPhone());
        channel.setCommissionRate(req.getCommissionRate());
        if (isCreate) {
            customerChannelMapper.insert(channel);
        } else {
            customerChannelMapper.updateById(channel);
        }

        ChannelCreateResp resp = new ChannelCreateResp();
        resp.setChannelId(String.valueOf(channel.getChannelId()));
        resp.setChannelCode(channel.getChannelCode());
        resp.setCreatedAt(DateUtils.format(channel.getCreatedTime()));
        return resp;
    }

    /** 按ID查询客户，不存在则抛出业务异常 */
    private Customer getByIdOrThrow(Long customerId) {
        if (customerId == null) {
            throw new BizException(ResultCode.BAD_REQUEST, "客户ID不能为空");
        }
        Customer customer = customerMapper.selectById(customerId);
        if (customer == null) {
            throw new BizException(ResultCode.NOT_FOUND, "客户不存在");
        }
        return customer;
    }

    /** 生成唯一客户编号：CUS+时间戳+随机数 */
    private String generateCustomerNo() {
        String no = "CUS" + LocalDateTime.now().format(NO_FORMATTER) + ThreadLocalRandom.current().nextInt(1000, 9999);
        Long count = customerMapper.selectCount(
                new LambdaQueryWrapper<Customer>().eq(Customer::getCustomerNo, no));
        return count > 0 ? generateCustomerNo() : no;
    }
}