package com.example.skyfast_2_0.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class RevenueDTO {
    private double totalRevenue;
    private Map<String, Double> revenueByCategory;
}
