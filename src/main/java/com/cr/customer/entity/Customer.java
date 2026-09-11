package com.cr.customer.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 客户实体
 */
@Data
@TableName("t_customer")
public class Customer {

    @TableId(value = "customer_id", type = IdType.AUTO)
    private Long customerId;

    private String customerNo;

    private Integer customerType;

    private String name;

    private Integer idType;

    private String idNo;

    private String phone;

    private String email;

    private String address;

    private Integer riskLevel;

    private Integer creditScore;

    private String tags;

    private String sourceChannel;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedTime;

    @TableLogic
    private Integer deleted;
}