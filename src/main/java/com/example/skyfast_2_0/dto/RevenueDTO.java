package com.example.skyfast_2_0.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RevenueDTO {
    private Float totalRevenue;
    private String category;
    private Float categoryRevenue;
}