package com.cr.customer.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 客户渠道实体
 */
@Data
@TableName("t_customer_channel")
public class CustomerChannel {

    @TableId(value = "channel_id", type = IdType.AUTO)
    private Long channelId;

    private String channelName;

    private String channelCode;

    private String contactPerson;

    private String contactPhone;

    private BigDecimal commissionRate;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedTime;

    @TableLogic
    private Integer deleted;
}