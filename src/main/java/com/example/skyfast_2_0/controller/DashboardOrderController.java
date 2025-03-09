package com.example.skyfast_2_0.controller;

import com.example.skyfast_2_0.dto.DashboardOrderTrendDTO;
import com.example.skyfast_2_0.service.DashboardOrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/dashboard/orders")
public class DashboardOrderController {
    private final DashboardOrderService dashboardOrderService;

    public DashboardOrderController(DashboardOrderService dashboardOrderService) {
        this.dashboardOrderService = dashboardOrderService;
    }

    // API trả về JSON
    @GetMapping("/data")
    @ResponseBody
    public Map<String, Object> getOrderStats(@RequestParam(defaultValue = "7") int days) {
        return dashboardOrderService.getOrderStats(days);
    }
}
