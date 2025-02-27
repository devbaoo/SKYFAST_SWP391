package com.example.skyfast_2_0.service;

import com.example.skyfast_2_0.dto.BookingDTO;
import com.example.skyfast_2_0.dto.TicketDTO;
import com.example.skyfast_2_0.entity.Booking;
import com.example.skyfast_2_0.entity.Ticket;
import com.example.skyfast_2_0.repository.BookingRepository;
import com.example.skyfast_2_0.repository.TicketRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingService {

    @Autowired
    private TicketRepository ticketRepository;

    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public List<BookingDTO> getAllBookings() {
        return bookingRepository.findAll().stream()
                .map(booking -> new BookingDTO(
                        booking.getId(),
                        booking.getTotalPrice(),
                        booking.getBookingDate(),
                        booking.getBookingStatus(),
                        booking.getUser() != null ? booking.getUser().getId() : null,
                        booking.getStatus()))
                .collect(Collectors.toList());
    }

    public BookingDTO getBookingById(Integer id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Booking not found"));
        Integer userId = (booking.getUser() != null) ? booking.getUser().getId() : null;
        return new BookingDTO(booking.getId(), booking.getTotalPrice(), booking.getBookingDate(), booking.getBookingStatus(), userId, booking.getStatus());
    }

    public BookingDTO updateBookingStatus(Integer id, String status) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Booking not found"));
        booking.setStatus(status);
        bookingRepository.save(booking);
        Integer userId = (booking.getUser() != null) ? booking.getUser().getId() : null;
        return new BookingDTO(booking.getId(), booking.getTotalPrice(), booking.getBookingDate(), booking.getBookingStatus(), userId, booking.getStatus());
    }

    public void softDeleteBooking(Integer id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Booking not found"));
        if ("ACTIVE".equals(booking.getStatus())) {
            booking.setStatus("INACTIVE");
            bookingRepository.save(booking);
        } else {
            throw new IllegalStateException("Booking is already inactive or in a non-deletable state");
        }
    }

    public List<TicketDTO> getTicketsByBookingId(Integer bookingId) {
        List<Ticket> tickets = ticketRepository.findByBookingId(bookingId);
        return tickets.stream().map(TicketDTO::new).collect(Collectors.toList());
    }
}