package com.example.skyfast_2_0.service;

import com.example.skyfast_2_0.dto.DashboardOrderTrendDTO;
import com.example.skyfast_2_0.repository.DashboardOrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DashboardOrderService {
    private final DashboardOrderRepository dashboardOrderRepository;

    public DashboardOrderService(DashboardOrderRepository dashboardOrderRepository) {
        this.dashboardOrderRepository = dashboardOrderRepository;
    }

    public DashboardOrderTrendDTO getOrderTrendPercentage(int days) {
        LocalDate today = LocalDate.now();
        LocalDate startCurrentPeriod = today.minusDays(days);
        LocalDate startPreviousPeriod = startCurrentPeriod.minusDays(days);

        long currentOrders = dashboardOrderRepository.countSuccessfulOrders(startCurrentPeriod, today);
        long previousOrders = dashboardOrderRepository.countSuccessfulOrders(startPreviousPeriod, startCurrentPeriod);

        double trendPercentage;
        if (previousOrders == 0) {
            trendPercentage = currentOrders > 0 ? 100.0 : 0.0;
        } else {
            trendPercentage = ((double) (currentOrders - previousOrders) / previousOrders) * 100;
        }

        return new DashboardOrderTrendDTO(trendPercentage);
    }
}
