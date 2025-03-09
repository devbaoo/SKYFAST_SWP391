package com.example.skyfast_2_0.controller;

import com.example.skyfast_2_0.dto.OrderStatisticsDTO;
import com.example.skyfast_2_0.service.DashboardBookingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard/bookings")
public class DashboardBookingController {
    private final DashboardBookingService dashboardBookingService;

    public DashboardBookingController(DashboardBookingService dashboardBookingService) {
        this.dashboardBookingService = dashboardBookingService;
    }

    @GetMapping
    public String getDashboard(Model model) {
        OrderStatisticsDTO orderStats = dashboardBookingService.getOrderStatistics();
        model.addAttribute("orderStats", orderStats);
        return "Dashboard"; // Trả về trang Thymeleaf
    }
}
