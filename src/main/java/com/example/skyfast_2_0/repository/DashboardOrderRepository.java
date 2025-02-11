package com.example.skyfast_2_0.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.skyfast_2_0.entity.Booking;

import java.time.LocalDate;

@Repository
public interface DashboardOrderRepository extends JpaRepository<Booking, Long> {

    @Query("SELECT COUNT(b) FROM Booking b WHERE b.paymentStatus = 'Completed' AND b.bookingDate BETWEEN :startDate AND :endDate")
    long countSuccessfulOrders(LocalDate startDate, LocalDate endDate);

}
