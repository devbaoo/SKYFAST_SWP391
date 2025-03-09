package com.example.skyfast_2_0.repository;

import com.example.skyfast_2_0.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DashboardRevenueRepository extends JpaRepository<Booking, Integer> {

    @Query("SELECT SUM(b.totalPrice) FROM Booking b " +
            "JOIN Payment p ON b.id = p.booking.id " +
            "WHERE p.paymentStatus = 'Completed'")
    Float getTotalRevenue();

    @Query("SELECT cc.name, SUM(b.totalPrice) FROM Booking b " +
            "JOIN Payment p ON b.id = p.booking.id " +
            "JOIN Ticket t ON b.id = t.booking.id " +
            "JOIN t.seat s " +
            "JOIN s.classCategory cc " +
            "WHERE p.paymentStatus = 'Completed' " +
            "GROUP BY cc.name")
    List<Object[]> getRevenueByCategory();

    @Query("SELECT MONTH(b.bookingDate), SUM(b.totalPrice) FROM Booking b " +
            "JOIN Payment p ON b.id = p.booking.id " +
            "WHERE p.paymentStatus = 'Completed' " +
            "GROUP BY MONTH(b.bookingDate)")
    List<Object[]> getRevenueByMonth();
}
