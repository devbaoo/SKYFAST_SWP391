package com.example.skyfast_2_0.service;

import com.example.skyfast_2_0.dto.CustomerStartDTO;
import com.example.skyfast_2_0.repository.DashboardCustomerRepository;
import org.springframework.stereotype.Service;


import java.time.LocalDate;

@Service
public class DashboardCustomerService {
    private final DashboardCustomerRepository dashboardCustomerRepository;

    public DashboardCustomerService(DashboardCustomerRepository dashboardCustomerRepository) {
        this.dashboardCustomerRepository = dashboardCustomerRepository;
    }

    public CustomerStartDTO getCustomerStats(int days) {
        try {
            LocalDate startDate = LocalDate.now().minusDays(days); // Dùng LocalDate thay vì Instant
            long newlyRegistered = dashboardCustomerRepository.countNewlyRegistered(startDate);
            long newlyBought = dashboardCustomerRepository.countNewlyBought(startDate);
            return new CustomerStartDTO(newlyRegistered, newlyBought);
        } catch (Exception e) {
            System.err.println("Lỗi khi truy vấn database: " + e.getMessage());
            return new CustomerStartDTO(0, 0);
        }
    }
}
