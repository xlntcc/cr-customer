package com.cr.customer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 客户中心服务启动类
 */
@SpringBootApplication
@MapperScan("com.cr.customer.mapper")
@EnableFeignClients(basePackages = "com.cr.customer.api")
public class CustomerApplication {

    /** 启动入口 */
    public static void main(String[] args) {
        SpringApplication.run(CustomerApplication.class, args);
    }
}