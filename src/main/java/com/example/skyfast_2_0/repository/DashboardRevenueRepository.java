package com.example.skyfast_2_0.repository;

import com.example.skyfast_2_0.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DashboardRevenueRepository extends JpaRepository<Booking, Integer> {
    @Query("SELECT SUM(b.totalPrice) FROM Booking b WHERE b.paymentStatus = 'Completed'")
    Integer getTotalRevenue();

    @Query("SELECT t.ticketType, SUM(b.totalPrice) FROM Booking b " +
            "JOIN Ticket t ON b.id = t.booking.id " +
            "WHERE b.paymentStatus = 'Completed' " +
            "GROUP BY t.ticketType")
    List<Object[]> getRevenueByCategory();
}
