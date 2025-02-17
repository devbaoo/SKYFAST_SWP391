package com.example.skyfast_2_0.service;

import com.example.skyfast_2_0.dto.CustomerStartDTO;
import com.example.skyfast_2_0.repository.DashboardCustomerRepository;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import java.time.LocalDate;

@Slf4j
@Service
public class DashboardCustomerService {
    private final DashboardCustomerRepository dashboardCustomerRepository;

    public DashboardCustomerService(DashboardCustomerRepository dashboardCustomerRepository) {
        this.dashboardCustomerRepository = dashboardCustomerRepository;
    }

    public CustomerStartDTO getCustomerStats(int days) {
        try {
            LocalDate startDate = LocalDate.now().minusDays(days);
            log.info("Calculating customer stats from: {}", startDate);

            // Get total counts
            long totalCustomers = dashboardCustomerRepository.countTotalCustomers();
            long totalBuyingCustomers = dashboardCustomerRepository.countTotalBuyingCustomers();

            // Get new counts
            long newlyRegistered = dashboardCustomerRepository.countNewlyRegistered(startDate);
            long newlyBought = dashboardCustomerRepository.countNewlyBought(startDate);

            // Calculate buying rate
            double buyingRate = totalCustomers > 0
                    ? (double) totalBuyingCustomers / totalCustomers * 100
                    : 0.0;

            log.info("Stats - Total: {}, Buying: {}, New Reg: {}, New Buying: {}, Rate: {}%",
                    totalCustomers, totalBuyingCustomers, newlyRegistered, newlyBought, buyingRate);

            return new CustomerStartDTO(
                    totalCustomers,
                    totalBuyingCustomers,
                    newlyRegistered,
                    newlyBought,
                    buyingRate
            );
        } catch (Exception e) {
            log.error("Error while getting customer stats", e);
            return new CustomerStartDTO(0, 0, 0, 0, 0.0);
        }
    }
}