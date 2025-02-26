//package com.example.skyfast_2_0.service;
//
//import org.springframework.stereotype.Service;
//import org.springframework.beans.factory.annotation.Autowired;
//import com.example.skyfast_2_0.repository.DashboardRevenueRepository;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Service
//public class DashboardRevenueService {
//    @Autowired
//    private DashboardRevenueRepository dashboardRevenueRepository;
//
//    public Map<String, Object> getRevenueStats() {
//        Map<String, Object> response = new HashMap<>();
//
//        // Get total revenue
//        Float totalRevenue = dashboardRevenueRepository.getTotalRevenue();
//        response.put("totalRevenue", totalRevenue != null ? totalRevenue : 0.0f);
//
//        // Get revenue by class category
//        List<Object[]> categoryRevenueList = dashboardRevenueRepository.getRevenueByCategory();
//        Map<String, Float> categoryRevenue = new HashMap<>();
//        for (Object[] row : categoryRevenueList) {
//            String category = (String) row[0];
//            Float revenue = row[1] != null ? ((Number) row[1]).floatValue() : 0.0f;
//            categoryRevenue.put(category, revenue);
//        }
//        response.put("revenueByCategory", categoryRevenue);
//
//        // Get revenue by month
//        List<Object[]> monthlyRevenueList = dashboardRevenueRepository.getRevenueByMonth();
//        Map<Integer, Float> monthlyRevenue = new HashMap<>();
//        for (Object[] row : monthlyRevenueList) {
//            Integer month = (Integer) row[0];
//            Float revenue = row[1] != null ? ((Number) row[1]).floatValue() : 0.0f;
//            monthlyRevenue.put(month, revenue);
//        }
//        response.put("revenueByMonth", monthlyRevenue);
//
//        return response;
//    }
//}