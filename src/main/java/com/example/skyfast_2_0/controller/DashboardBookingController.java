package com.example.skyfast_2_0.controller;

import com.example.skyfast_2_0.dto.OrderStatisticsDTO;
import com.example.skyfast_2_0.service.DashboardBookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard/bookings")
public class DashboardBookingController {
    private final DashboardBookingService dashboardBookingService;

    public DashboardBookingController(DashboardBookingService dashboardBookingService) {
        this.dashboardBookingService = dashboardBookingService;
    }

    @GetMapping("/stats")
    public ResponseEntity<OrderStatisticsDTO> getOrderStatistics() {
        return ResponseEntity.ok(dashboardBookingService.getOrderStatistics());
    }
}