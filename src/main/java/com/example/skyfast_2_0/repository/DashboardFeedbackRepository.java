package com.example.skyfast_2_0.repository;

import com.example.skyfast_2_0.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DashboardFeedbackRepository extends JpaRepository<Feedback, Integer> {
    @Query("SELECT COALESCE(AVG(f.rating), 0.0) FROM Feedback f")
    Double findAverageStarTotal();

    @Query("SELECT a.airlineName, COALESCE(AVG(f.rating), 0.0) " +
            "FROM Feedback f " +
            "JOIN f.flight fl " +
            "JOIN fl.airline a " +
            "GROUP BY a.airlineName")
    List<Object[]> findAverageStarByAirline();

    @Query("SELECT f.rating, COUNT(f) " +
            "FROM Feedback f " +
            "GROUP BY f.rating " +
            "ORDER BY f.rating")
    List<Object[]> findRatingDistribution();
}