//package com.example.skyfast_2_0.service;
//
//import org.springframework.stereotype.Service;
//import lombok.extern.slf4j.Slf4j;
//import com.example.skyfast_2_0.dto.OrderStatisticsDTO;
//import com.example.skyfast_2_0.repository.DashboardBookingRepository;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Slf4j
//@Service
//public class DashboardBookingService {
//    private final DashboardBookingRepository dashboardBookingRepository;
//
//    public DashboardBookingService(DashboardBookingRepository dashboardBookingRepository) {
//        this.dashboardBookingRepository = dashboardBookingRepository;
//    }
//
//    public OrderStatisticsDTO getOrderStatistics() {
//        try {
//            // Get counts for each status
//            long confirmedCount = dashboardBookingRepository.countSuccessBookings();
//            long cancelledCount = dashboardBookingRepository.countCancelledBookings();
//            long pendingCount = dashboardBookingRepository.countPendingBookings();
//            long totalCount = confirmedCount + cancelledCount + pendingCount;
//
//            // Get detailed status distribution
//            List<Object[]> statusCounts = dashboardBookingRepository.getBookingStatusCounts();
//            Map<String, Long> statusDistribution = new HashMap<>();
//
//            for (Object[] row : statusCounts) {
//                String status = (String) row[0];
//                Long count = ((Number) row[1]).longValue();
//                statusDistribution.put(status, count);
//                log.info("Status: {}, Count: {}", status, count);
//            }
//
//            log.info("Booking statistics - Confirmed: {}, Cancelled: {}, Pending: {}, Total: {}",
//                    confirmedCount, cancelledCount, pendingCount, totalCount);
//
//            return new OrderStatisticsDTO(
//                    confirmedCount,
//                    cancelledCount,
//                    pendingCount,
//                    totalCount,
//                    statusDistribution
//            );
//        } catch (Exception e) {
//            log.error("Error getting booking statistics", e);
//            return new OrderStatisticsDTO(0, 0, 0, 0, new HashMap<>());
//        }
//    }
//}