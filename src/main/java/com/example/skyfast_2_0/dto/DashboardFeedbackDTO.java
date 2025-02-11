package com.example.skyfast_2_0.dto;

import lombok.Data;
import lombok.AllArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor

public class DashboardFeedbackDTO {
    private double averageStarTotal;
    private Map<String, Double> averageStarByCategory;
}
