package com.example.skyfast_2_0.controller;

import com.example.skyfast_2_0.dto.OrderStatisticsDTO;
import com.example.skyfast_2_0.service.DashboardBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard/orders")

public class  DashboardBookingController {
    @Autowired
    private DashboardBookingService dashboardBookingService;

    @GetMapping
    public OrderStatisticsDTO getOrderStatistics() {
        return dashboardBookingService.getOrderStatistics();
    }
}
