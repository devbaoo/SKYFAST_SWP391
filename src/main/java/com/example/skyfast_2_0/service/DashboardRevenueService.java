package com.example.skyfast_2_0.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.skyfast_2_0.dto.RevenueDTO;
import com.example.skyfast_2_0.repository.DashboardRevenueRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DashboardRevenueService {
    @Autowired
    private DashboardRevenueRepository dasboardRevenueRepository;

    public Map<String, Object> getRevenueStats() {
        Integer totalRevenue = dasboardRevenueRepository.getTotalRevenue();
        List<Object[]> categoryRevenueList = dasboardRevenueRepository.getRevenueByCategory();

        Map<String, Integer> categoryRevenue = new HashMap<>();
        for (Object[] row : categoryRevenueList) {
            categoryRevenue.put((String) row[0], ((Number) row[1]).intValue());
        }

        Map<String, Object> response = new HashMap<>();
        response.put("totalRevenue", totalRevenue);
        response.put("revenueByCategory", categoryRevenue);
        return response;
    }
}
