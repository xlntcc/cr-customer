package com.cr.customer.dto.resp;

import lombok.Builder;
import lombok.Data;

/**
 * 客户360画像响应
 */
@Data
@Builder
public class CustomerProfileResp {

    private BasicInfo basicInfo;

    private OrderStats orderStats;

    private RiskScore riskScore;

    private AssetInfo assetInfo;

    @Data
    @Builder
    public static class BasicInfo {

        private String customerId;

        private String customerNo;

        private String customerType;

        private String name;

        private String idType;

        private String phone;

        private String email;

        private String address;

        private String riskLevel;

        private Integer creditScore;

        private String sourceChannel;

        private String createdAt;
    }

    @Data
    @Builder
    public static class OrderStats {

        private Long totalOrders;

        private Long activeOrders;

        private Long completedOrders;

        private Long overdueOrders;
    }

    @Data
    @Builder
    public static class RiskScore {

        private Integer score;

        private String riskLevel;

        private String suggestion;
    }

    @Data
    @Builder
    public static class AssetInfo {

        private Long vehicleCount;

        private String mortgageStatus;
    }
}