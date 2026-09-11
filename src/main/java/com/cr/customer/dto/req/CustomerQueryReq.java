package com.cr.customer.dto.req;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

/**
 * 分页查询客户请求
 */
@Data
public class CustomerQueryReq {

    /** 客户类型：个人/企业 */
    private String customerType;

    /** 模糊查询关键字 */
    private String keyword;

    /** 风险等级：低/中/高 */
    private String riskLevel;

    /** 页码，从1开始 */
    @Min(value = 1, message = "页码最小为1")
    private Integer pageNum = 1;

    /** 每页大小 */
    @Min(value = 1, message = "每页大小最小为1")
    @Max(value = 100, message = "每页大小最大为100")
    private Integer pageSize = 20;
}