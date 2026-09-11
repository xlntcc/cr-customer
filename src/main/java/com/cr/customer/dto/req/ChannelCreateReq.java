package com.cr.customer.dto.req;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 渠道创建/更新请求
 */
@Data
public class ChannelCreateReq {

    /** 渠道名称 */
    @NotBlank(message = "渠道名称不能为空")
    private String channelName;

    /** 渠道编码 */
    @NotBlank(message = "渠道编码不能为空")
    private String channelCode;

    /** 联系人 */
    private String contactPerson;

    /** 联系电话 */
    private String contactPhone;

    /** 佣金比例 */
    private BigDecimal commissionRate;
}