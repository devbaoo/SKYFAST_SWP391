package com.example.skyfast_2_0.controller;

import com.example.skyfast_2_0.dto.BookingDTO;
import com.example.skyfast_2_0.dto.TicketDTO;
import com.example.skyfast_2_0.service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping
    public ResponseEntity<List<BookingDTO>> getAllBookings() {
        List<BookingDTO> bookings = bookingService.getAllBookings();
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingDTO> getBookingById(@PathVariable Integer id) {
        BookingDTO booking = bookingService.getBookingById(id);
        return ResponseEntity.ok(booking);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookingDTO> updateBooking(@PathVariable Integer id, @RequestBody BookingDTO bookingDTO) {
        BookingDTO updatedBooking = bookingService.updateBookingStatus(id, bookingDTO.getStatus());
        return ResponseEntity.ok(updatedBooking);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> softDeleteBooking(@PathVariable Integer id) {
        bookingService.updateBookingStatus(id, "INACTIVE");
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/tickets")
    public ResponseEntity<List<TicketDTO>> getTicketsByBookingId(@PathVariable Integer id) {
        List<TicketDTO> tickets = bookingService.getTicketsByBookingId(id);
        return ResponseEntity.ok(tickets);
    }
}