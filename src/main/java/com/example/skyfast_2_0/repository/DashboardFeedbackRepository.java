package com.example.skyfast_2_0.repository;

import com.example.skyfast_2_0.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface DashboardFeedbackRepository extends JpaRepository<Feedback, Long> {
    @Query("SELECT AVG(f.rating) FROM Feedback f")
    Double findAverageStarTotal();

    @Query("SELECT f.flight.airline.airlineName, AVG(f.rating) FROM Feedback f GROUP BY f.flight.airline.airlineName")
    List<Object[]> findAverageStarByCategoryRaw();

}
