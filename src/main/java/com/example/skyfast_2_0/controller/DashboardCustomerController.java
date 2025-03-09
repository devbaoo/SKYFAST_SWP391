package com.example.skyfast_2_0.controller;

import com.example.skyfast_2_0.dto.CustomerStartDTO;
import com.example.skyfast_2_0.service.DashboardCustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/dashboard/customers")
public class DashboardCustomerController {
    private final DashboardCustomerService dashboardCustomerService;

    public DashboardCustomerController(DashboardCustomerService customerService) {
        this.dashboardCustomerService = customerService;
    }

    // API trả về JSON
    @GetMapping("/data")
    @ResponseBody
    public CustomerStartDTO getCustomerStats(@RequestParam(defaultValue = "7") int days) {
        return dashboardCustomerService.getCustomerStats(days);
    }
}
