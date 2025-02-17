package com.example.skyfast_2_0.repository;

import com.example.skyfast_2_0.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
@Repository
public interface DashboardCustomerRepository extends JpaRepository<User, Integer> {

    @Query(value = "SELECT COUNT(*) FROM User u " +
            "WHERE u.role = 'CUSTOMER' " +
            "AND (u.is_deleted = false OR u.is_deleted IS NULL)", nativeQuery = true)
    long countTotalCustomers();

    @Query(value = "SELECT COUNT(DISTINCT b.user_id) FROM Booking b " +
            "INNER JOIN User u ON b.user_id = u.id " +
            "WHERE u.role = 'CUSTOMER' " +
            "AND (u.is_deleted = false OR u.is_deleted IS NULL)", nativeQuery = true)
    long countTotalBuyingCustomers();

    @Query(value = "SELECT COUNT(*) FROM User u " +
            "WHERE u.role = 'CUSTOMER' " +
            "AND (u.is_deleted = false OR u.is_deleted IS NULL) " +
            "AND DATE(u.created_at) >= :startDate", nativeQuery = true)
    long countNewlyRegistered(@Param("startDate") LocalDate startDate);

    @Query(value = "SELECT COUNT(DISTINCT b.user_id) FROM Booking b " +
            "INNER JOIN User u ON b.user_id = u.id " +
            "WHERE u.role = 'CUSTOMER' " +
            "AND (u.is_deleted = false OR u.is_deleted IS NULL) " +
            "AND DATE(b.booking_date) >= :startDate " +
            "AND NOT EXISTS (" +
            "    SELECT 1 FROM Booking b2 " +
            "    WHERE b2.user_id = b.user_id " +
            "    AND DATE(b2.booking_date) < :startDate" +
            ")", nativeQuery = true)
    long countNewlyBought(@Param("startDate") LocalDate startDate);
}