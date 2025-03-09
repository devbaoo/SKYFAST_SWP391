package com.example.skyfast_2_0.service;

import com.example.skyfast_2_0.dto.DashboardOrderTrendDTO;
import com.example.skyfast_2_0.repository.DashboardOrderRepository;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class DashboardOrderService {
    private final DashboardOrderRepository dashboardOrderRepository;

    public DashboardOrderService(DashboardOrderRepository dashboardOrderRepository) {
        this.dashboardOrderRepository = dashboardOrderRepository;
    }

    public Map<String, Object> getOrderStats(int days) {
        LocalDate today = LocalDate.now();
        LocalDate startDate = today.minusDays(days);

        // Debug: In ra tất cả booking status và dates
        List<Object[]> allBookings = dashboardOrderRepository.getAllBookingStatusAndDates();
        log.info("All bookings found: {}", allBookings.size());
        for (Object[] booking : allBookings) {
            log.info("Status: {}, Date: {}", booking[0], booking[1]);
        }

        List<Object[]> statusCounts = dashboardOrderRepository.getOrderStatusCount(startDate, today);
        log.info("Status counts for period {} to {}: {}", startDate, today, statusCounts.size());

        Map<String, Long> ordersByStatus = new HashMap<>();
        long totalOrders = 0;

        // Khởi tạo các trạng thái mặc định
        ordersByStatus.put("Confirmed", 0L);
        ordersByStatus.put("Pending", 0L);
        ordersByStatus.put("Cancelled", 0L);

        for (Object[] row : statusCounts) {
            String status = (String) row[0];
            Long count = ((Number) row[1]).longValue();
            ordersByStatus.put(status, count);
            totalOrders += count;
            log.info("Status: {}, Count: {}", status, count);
        }

        double trendPercentage = getOrderTrendPercentage(days).getTrendPercentage();

        Map<String, Object> response = new HashMap<>();
        response.put("totalOrders", totalOrders);
        response.put("ordersByStatus", ordersByStatus);
        response.put("trendPercentage", trendPercentage);

        log.info("Final response: {}", response);
        return response;
    }

    public DashboardOrderTrendDTO getOrderTrendPercentage(int days) {
        LocalDate today = LocalDate.now();
        LocalDate startCurrentPeriod = today.minusDays(days);
        LocalDate startPreviousPeriod = startCurrentPeriod.minusDays(days);

        long currentOrders = dashboardOrderRepository.countSuccessfulOrders(startCurrentPeriod, today);
        long previousOrders = dashboardOrderRepository.countSuccessfulOrders(startPreviousPeriod, startCurrentPeriod);

        log.info("Current period orders: {}", currentOrders);
        log.info("Previous period orders: {}", previousOrders);

        double trendPercentage;
        if (previousOrders == 0) {
            trendPercentage = currentOrders > 0 ? 100.0 : 0.0;
        } else {
            trendPercentage = ((double) (currentOrders - previousOrders) / previousOrders) * 100;
        }

        return new DashboardOrderTrendDTO(trendPercentage);
    }
}