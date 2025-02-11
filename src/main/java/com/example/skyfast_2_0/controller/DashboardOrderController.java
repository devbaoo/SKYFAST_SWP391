package com.example.skyfast_2_0.controller;

import com.example.skyfast_2_0.dto.DashboardOrderTrendDTO;
import com.example.skyfast_2_0.service.DashboardOrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard/orders")
public class DashboardOrderController {
    private final DashboardOrderService dashboardOrderService;

    public DashboardOrderController(DashboardOrderService dashboardOrderService) {
        this.dashboardOrderService = dashboardOrderService;
    }

    @GetMapping("/trend")
    public DashboardOrderTrendDTO getOrderTrend(@RequestParam(defaultValue = "7") int days) {
        return dashboardOrderService.getOrderTrendPercentage(days);
    }
}
