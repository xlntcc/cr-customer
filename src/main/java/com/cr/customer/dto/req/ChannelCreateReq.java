package com.cr.customer.dto.req;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 渠道创建/更新请求
 */
@Data
public class ChannelCreateReq {

    @NotBlank(message = "渠道名称不能为空")
    private String channelName;

    @NotBlank(message = "渠道编码不能为空")
    private String channelCode;

    private String contactPerson;

    private String contactPhone;

    private BigDecimal commissionRate;
}