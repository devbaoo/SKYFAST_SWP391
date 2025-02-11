package com.example.skyfast_2_0.controller;

import com.example.skyfast_2_0.dto.RevenueDTO;
import com.example.skyfast_2_0.service.DashboardRevenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/dashboard/revenue")
public class DashboardRevenueController {
    @Autowired
    private DashboardRevenueService dashboardRevenueService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getRevenueStats() {
        return ResponseEntity.ok(dashboardRevenueService.getRevenueStats());
    }
}
