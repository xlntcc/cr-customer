package com.cr.customer.dto.resp;

import lombok.Data;

/**
 * 渠道创建/更新响应
 */
@Data
public class ChannelCreateResp {

    /** 渠道ID */
    private String channelId;

    /** 渠道编码 */
    private String channelCode;

    /** 创建时间 */
    private String createdAt;
}