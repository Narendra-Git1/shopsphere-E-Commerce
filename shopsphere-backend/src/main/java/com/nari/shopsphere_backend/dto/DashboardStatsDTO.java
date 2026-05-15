package com.nari.shopsphere_backend.dto;

import lombok.Data;

@Data
public class DashboardStatsDTO {

    private Long totalUsers;

    private Long totalProducts;

    private Long totalOrders;

    private Double totalRevenue;
}