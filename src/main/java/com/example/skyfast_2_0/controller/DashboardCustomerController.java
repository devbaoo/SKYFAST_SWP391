package com.example.skyfast_2_0.controller;

import com.example.skyfast_2_0.dto.CustomerStartDTO;
import com.example.skyfast_2_0.service.DashboardCustomerService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/dashboard/customers")
public class DashboardCustomerController {
    private final DashboardCustomerService dashboardCustomerService;

    public DashboardCustomerController(DashboardCustomerService customerService) {
        this.dashboardCustomerService = customerService;
    }

    @GetMapping("/stats")
    public CustomerStartDTO getCustomerStats(@RequestParam(defaultValue = "7") int days) {
        return dashboardCustomerService.getCustomerStats(days);
    }
}
