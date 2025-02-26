//package com.example.skyfast_2_0.repository;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//import com.example.skyfast_2_0.entity.Booking;
//
//import java.time.LocalDate;
//import java.util.List;
//
//@Repository
//public interface DashboardOrderRepository extends JpaRepository<Booking, Integer> {
//
//    @Query("SELECT COUNT(b) FROM Booking b " +
//            "WHERE b.bookingStatus = 'Confirmed' " +
//            "AND b.bookingDate BETWEEN :startDate AND :endDate")
//    long countSuccessfulOrders(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
//
//    @Query(value = "SELECT " +
//            "COALESCE(b.booking_status, 'Unknown') as status, " +
//            "COUNT(*) as count " +
//            "FROM Booking b " +
//            "WHERE b.booking_date BETWEEN :startDate AND :endDate " +
//            "GROUP BY b.booking_status", nativeQuery = true)
//    List<Object[]> getOrderStatusCount(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
//
//    // Thêm query để debug
//    @Query("SELECT b.bookingStatus, b.bookingDate FROM Booking b")
//    List<Object[]> getAllBookingStatusAndDates();
//}