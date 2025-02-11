package com.example.skyfast_2_0.repository;

import com.example.skyfast_2_0.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;


@Repository
public interface DashboardCustomerRepository extends JpaRepository<User, Integer> {

    @Query("SELECT COUNT(u) FROM User u WHERE DATE(u.createdAt) >= :startDate")
    long countNewlyRegistered(LocalDate startDate);

    @Query("SELECT COUNT(DISTINCT b.user.id) FROM Booking b WHERE DATE(b.bookingDate) >= :startDate " +
            "AND b.user IS NOT NULL " +
            "AND b.user.id NOT IN (SELECT DISTINCT b2.user.id FROM Booking b2 WHERE DATE(b2.bookingDate) < :startDate " +
            "AND b2.user IS NOT NULL)")
    long countNewlyBought(LocalDate startDate);
}
