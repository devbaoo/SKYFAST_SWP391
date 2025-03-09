package com.example.skyfast_2_0.dto;

import lombok.Data;
import lombok.AllArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
public class OrderStatisticsDTO {
    private long confirmedCount;
    private long cancelledCount;
    private long pendingCount;
    private long totalCount;
    private Map<String, Long> statusDistribution;
}