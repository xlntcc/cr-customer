package com.cr.customer.dto.req;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

/**
 * 创建客户请求
 */
@Data
public class CustomerCreateReq {

    @NotBlank(message = "客户类型不能为空")
    private String customerType;

    @NotBlank(message = "客户名称不能为空")
    private String name;

    @NotBlank(message = "证件类型不能为空")
    private String idType;

    @NotBlank(message = "证件号码不能为空")
    private String idNumber;

    @NotBlank(message = "手机号不能为空")
    private String phone;

    private String email;

    private String address;

    private List<String> tags;

    private String sourceChannel;
}