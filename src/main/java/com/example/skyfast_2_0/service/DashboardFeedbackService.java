package com.example.skyfast_2_0.service;

import com.example.skyfast_2_0.dto.DashboardFeedbackDTO;
import com.example.skyfast_2_0.repository.DashboardFeedbackRepository;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class DashboardFeedbackService {
    private final DashboardFeedbackRepository dashboardFeedbackRepository;

    public DashboardFeedbackService(DashboardFeedbackRepository dashboardFeedbackRepository) {
        this.dashboardFeedbackRepository = dashboardFeedbackRepository;
    }

    public DashboardFeedbackDTO getFeedbackStats() {
        // Get average star total
        Double averageStarTotal = dashboardFeedbackRepository.findAverageStarTotal();
        log.info("Average star total: {}", averageStarTotal);

        // Get average star by airline
        List<Object[]> airlineResults = dashboardFeedbackRepository.findAverageStarByAirline();
        Map<String, Double> averageStarByAirline = new HashMap<>();
        for (Object[] row : airlineResults) {
            String airlineName = row[0] != null ? row[0].toString() : "Unknown";
            Double averageStar = row[1] instanceof Number ? ((Number) row[1]).doubleValue() : 0.0;
            averageStarByAirline.put(airlineName, averageStar);
        }
        log.info("Average star by airline: {}", averageStarByAirline);

        // Get rating distribution
        List<Object[]> distributionResults = dashboardFeedbackRepository.findRatingDistribution();
        Map<Integer, Long> ratingDistribution = new HashMap<>();
        long totalFeedbacks = 0;

        // Initialize all possible ratings with 0
        for (int i = 1; i <= 5; i++) {
            ratingDistribution.put(i, 0L);
        }

        // Fill in actual counts
        for (Object[] row : distributionResults) {
            Integer rating = ((Number) row[0]).intValue();
            Long count = ((Number) row[1]).longValue();
            ratingDistribution.put(rating, count);
            totalFeedbacks += count;
        }
        log.info("Rating distribution: {}", ratingDistribution);
        log.info("Total feedbacks: {}", totalFeedbacks);

        return new DashboardFeedbackDTO(
                averageStarTotal,
                averageStarByAirline,
                ratingDistribution,
                totalFeedbacks
        );
    }
}