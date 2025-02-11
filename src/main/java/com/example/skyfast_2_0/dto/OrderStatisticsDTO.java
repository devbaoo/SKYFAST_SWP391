package com.example.skyfast_2_0.dto;

import lombok.Data;
import lombok.AllArgsConstructor;



@Data
@AllArgsConstructor


public class OrderStatisticsDTO {
    private long successCount;
    private long cancelledCount;
    private long submittedCount;
}
