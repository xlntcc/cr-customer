package com.cr.customer.dto.req;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

/**
 * 创建客户请求
 */
@Data
public class CustomerCreateReq {

    /** 客户类型：个人/企业 */
    @NotBlank(message = "客户类型不能为空")
    private String customerType;

    /** 客户名称 */
    @NotBlank(message = "客户名称不能为空")
    private String name;

    /** 证件类型：身份证/营业执照 */
    @NotBlank(message = "证件类型不能为空")
    private String idType;

    /** 证件号码 */
    @NotBlank(message = "证件号码不能为空")
    private String idNumber;

    /** 手机号 */
    @NotBlank(message = "手机号不能为空")
    private String phone;

    /** 邮箱 */
    private String email;

    /** 联系地址 */
    private String address;

    /** 客户标签列表 */
    private List<String> tags;

    /** 来源渠道 */
    private String sourceChannel;
}