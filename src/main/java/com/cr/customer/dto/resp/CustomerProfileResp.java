package com.cr.customer.dto.resp;

import lombok.Builder;
import lombok.Data;

/**
 * 客户360画像响应
 */
@Data
@Builder
public class CustomerProfileResp {

    /** 基本信息 */
    private BasicInfo basicInfo;

    /** 订单统计 */
    private OrderStats orderStats;

    /** 风控数据 */
    private RiskScore riskScore;

    /** 资产信息 */
    private AssetInfo assetInfo;

    /** 基本信息 */
    @Data
    @Builder
    public static class BasicInfo {

        /** 客户ID */
        private String customerId;

        /** 客户编号 */
        private String customerNo;

        /** 客户类型：个人/企业 */
        private String customerType;

        /** 客户名称 */
        private String name;

        /** 证件类型 */
        private String idType;

        /** 手机号(脱敏) */
        private String phone;

        /** 邮箱 */
        private String email;

        /** 联系地址 */
        private String address;

        /** 风险等级：低/中/高 */
        private String riskLevel;

        /** 信用评分 */
        private Integer creditScore;

        /** 来源渠道 */
        private String sourceChannel;

        /** 创建时间 */
        private String createdAt;
    }

    /** 订单统计 */
    @Data
    @Builder
    public static class OrderStats {

        /** 订单总数 */
        private Long totalOrders;

        /** 进行中订单数 */
        private Long activeOrders;

        /** 已完成订单数 */
        private Long completedOrders;

        /** 逾期订单数 */
        private Long overdueOrders;
    }

    /** 风控数据 */
    @Data
    @Builder
    public static class RiskScore {

        /** 风控评分 */
        private Integer score;

        /** 风险等级 */
        private String riskLevel;

        /** 建议 */
        private String suggestion;
    }

    /** 资产信息 */
    @Data
    @Builder
    public static class AssetInfo {

        /** 车辆数 */
        private Long vehicleCount;

        /** 抵押状态 */
        private String mortgageStatus;
    }
}