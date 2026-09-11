package com.cr.customer.dto.req;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

/**
 * 客户标签请求
 */
@Data
public class CustomerTagReq {

    @NotEmpty(message = "标签列表不能为空")
    private List<String> tags;
}