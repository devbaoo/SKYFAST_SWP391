//package com.example.skyfast_2_0.repository;
//
//import com.example.skyfast_2_0.entity.Booking;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public interface DashboardBookingRepository extends JpaRepository<Booking, Integer> {
//
//    @Query("SELECT COUNT(b) FROM Booking b WHERE b.bookingStatus = 'Confirmed'")
//    long countSuccessBookings();
//
//    @Query("SELECT COUNT(b) FROM Booking b WHERE b.bookingStatus = 'Cancelled'")
//    long countCancelledBookings();
//
//    @Query("SELECT COUNT(b) FROM Booking b WHERE b.bookingStatus = 'Pending'")
//    long countPendingBookings();
//
//    // Query để debug
//    @Query("SELECT b.bookingStatus, COUNT(b) FROM Booking b GROUP BY b.bookingStatus")
//    List<Object[]> getBookingStatusCounts();
//}