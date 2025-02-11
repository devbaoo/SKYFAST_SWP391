    package com.example.skyfast_2_0.service;

    import com.example.skyfast_2_0.dto.DashboardFeedbackDTO;
    import com.example.skyfast_2_0.repository.DashboardFeedbackRepository;
    import org.springframework.stereotype.Service;

    import java.util.HashMap;
    import java.util.List;
    import java.util.Map;

    @Service
    public class DashboardFeedbackService {
        private final DashboardFeedbackRepository dashboardFeedbackRepository;

        public DashboardFeedbackService(DashboardFeedbackRepository dashboardFeedbackRepository) {
            this.dashboardFeedbackRepository = dashboardFeedbackRepository;
        }

        public DashboardFeedbackDTO getFeedbackStats() {
            Double averageStarTotal = dashboardFeedbackRepository.findAverageStarTotal();
            List<Object[]> rawResults = dashboardFeedbackRepository.findAverageStarByCategoryRaw();
            Map<String, Double> averageStarByCategory = new HashMap<>();

            for (Object[] row : rawResults) {
                String category = row[0] != null ? row[0].toString() : "Unknown";
                Double averageStar = row[1] instanceof Number ? ((Number) row[1]).doubleValue() : 0.0;
                averageStarByCategory.put(category, averageStar);
            }

            return new DashboardFeedbackDTO(
                    averageStarTotal != null ? averageStarTotal : 0.0,
                    averageStarByCategory
            );
        }


    }
