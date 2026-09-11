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

    /** 渠道ID */
    @TableId(value = "channel_id", type = IdType.AUTO)
    private Long channelId;

    /** 渠道名称 */
    private String channelName;

    /** 渠道编码 */
    private String channelCode;

    /** 联系人 */
    private String contactPerson;

    /** 联系电话 */
    private String contactPhone;

    /** 佣金比例 */
    private BigDecimal commissionRate;

    /** 创建时间 */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdTime;

    /** 更新时间 */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedTime;

    /** 逻辑删除标识 0-未删除 1-已删除 */
    @TableLogic
    private Integer deleted;
}