package com.cr.customer.dto.resp;

import lombok.Data;

/**
 * 渠道创建/更新响应
 */
@Data
public class ChannelCreateResp {

    private String channelId;

    private String channelCode;

    private String createdAt;
}