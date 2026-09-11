package com.cr.customer.support;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.cr.commons.enums.CustomerTypeEnum;
import com.cr.commons.enums.IdTypeEnum;
import com.cr.commons.enums.RiskLevelEnum;
import com.cr.commons.exception.BizException;
import com.cr.commons.result.ResultCode;
import com.cr.commons.util.DateUtils;
import com.cr.customer.dto.resp.CustomerResp;
import com.cr.customer.entity.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 客户对象转换器
 */
@Component
@RequiredArgsConstructor
public class CustomerConverter {

    private final ObjectMapper objectMapper;

    public CustomerResp toResp(Customer customer) {
        if (customer == null) {
            return null;
        }
        CustomerResp resp = new CustomerResp();
        resp.setCustomerId(String.valueOf(customer.getCustomerId()));
        resp.setCustomerNo(customer.getCustomerNo());
        CustomerTypeEnum customerType = CustomerTypeEnum.of(customer.getCustomerType());
        resp.setCustomerType(customerType == null ? null : customerType.name());
        resp.setName(customer.getName());
        IdTypeEnum idType = IdTypeEnum.of(customer.getIdType());
        resp.setIdType(idType == null ? null : idType.name());
        resp.setIdNumber(maskIdNumber(customer.getIdNo()));
        resp.setPhone(maskPhone(customer.getPhone()));
        resp.setEmail(customer.getEmail());
        resp.setAddress(customer.getAddress());
        RiskLevelEnum riskLevel = RiskLevelEnum.of(customer.getRiskLevel());
        resp.setRiskLevel(riskLevel == null ? null : riskLevel.name());
        resp.setCreditScore(customer.getCreditScore());
        resp.setTags(parseTags(customer.getTags()));
        resp.setSourceChannel(customer.getSourceChannel());
        resp.setCreatedAt(DateUtils.format(customer.getCreatedTime()));
        resp.setUpdatedAt(DateUtils.format(customer.getUpdatedTime()));
        return resp;
    }

    public List<String> parseTags(String tagsJson) {
        if (!StringUtils.hasText(tagsJson)) {
            return Collections.emptyList();
        }
        try {
            return objectMapper.readValue(tagsJson, new TypeReference<List<String>>() {
            });
        } catch (JsonProcessingException e) {
            throw new BizException(ResultCode.INTERNAL_ERROR, "标签数据解析失败");
        }
    }

    public String writeTags(List<String> tags) {
        try {
            return objectMapper.writeValueAsString(tags);
        } catch (JsonProcessingException e) {
            throw new BizException(ResultCode.INTERNAL_ERROR, "标签数据序列化失败");
        }
    }

    public String maskIdNumber(String idNumber) {
        if (!StringUtils.hasText(idNumber)) {
            return null;
        }
        if (idNumber.length() <= 8) {
            return "****";
        }
        return idNumber.substring(0, 4) + "****" + idNumber.substring(idNumber.length() - 4);
    }

    public String maskPhone(String phone) {
        if (!StringUtils.hasText(phone)) {
            return null;
        }
        if (phone.length() < 7) {
            return "****";
        }
        return phone.substring(0, 3) + "****" + phone.substring(phone.length() - 4);
    }

    public void appendTags(Customer customer, List<String> newTags) {
        List<String> merged = new ArrayList<>(parseTags(customer.getTags()));
        newTags.stream().filter(StringUtils::hasText).forEach(tag -> {
            if (!merged.contains(tag)) {
                merged.add(tag);
            }
        });
        customer.setTags(writeTags(merged));
    }
}