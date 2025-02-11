package com.example.skyfast_2_0.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.skyfast_2_0.dto.OrderStatisticsDTO;
import com.example.skyfast_2_0.repository.DashboardBookingRepository;

@Service
public class DashboardBookingService {
    @Autowired
    private DashboardBookingRepository dashboardBookingRepository;

    public OrderStatisticsDTO getOrderStatistics() {
        long success = dashboardBookingRepository.countByPaymentStatus("Completed");
        long cancelled = dashboardBookingRepository.countByPaymentStatus("Cancelled");
        long submitted = dashboardBookingRepository.countByPaymentStatus("Pending");
        return new OrderStatisticsDTO(success, cancelled, submitted);
    }
}
