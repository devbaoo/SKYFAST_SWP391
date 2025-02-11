package com.example.skyfast_2_0.controller;

import com.example.skyfast_2_0.dto.DashboardFeedbackDTO;
import com.example.skyfast_2_0.service.DashboardFeedbackService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard/feedbacks")
public class DashboardFeedbackController {
    private final DashboardFeedbackService dashboardFeedbackService;

    public DashboardFeedbackController(DashboardFeedbackService dashboardFeedbackService) {
        this.dashboardFeedbackService = dashboardFeedbackService;
    }

    @GetMapping("/stats")
    public DashboardFeedbackDTO getFeedbackStats() {
        return dashboardFeedbackService.getFeedbackStats();
    }
}
