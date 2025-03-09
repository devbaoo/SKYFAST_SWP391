package com.example.skyfast_2_0.controller;

import com.example.skyfast_2_0.service.DashboardRevenueService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/dashboard/revenue")
public class DashboardRevenueController {
    private final DashboardRevenueService dashboardRevenueService;

    public DashboardRevenueController(DashboardRevenueService dashboardRevenueService) {
        this.dashboardRevenueService = dashboardRevenueService;
    }

    // API trả về JSON
    @GetMapping("/data")
    @ResponseBody
    public Map<String, Object> getRevenueStats() {
        return dashboardRevenueService.getRevenueStats();
    }
}
