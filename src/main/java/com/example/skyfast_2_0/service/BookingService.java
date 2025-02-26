package com.example.skyfast_2_0.service;

import com.example.skyfast_2_0.dto.BookingDTO;
import com.example.skyfast_2_0.entity.Booking;
import com.example.skyfast_2_0.repository.BookingRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingService {

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
        Optional<Booking> bookingOptional = bookingRepository.findById(id);
        if (bookingOptional.isPresent()) {
            Booking booking = bookingOptional.get();
            Integer userId = (booking.getUser() != null) ? booking.getUser().getId() : null;
            return new BookingDTO(booking.getId(), booking.getTotalPrice(), booking.getBookingDate(), booking.getBookingStatus(), userId, booking.getStatus());
        }
        throw new EntityNotFoundException("Booking not found");
    }

    public BookingDTO updateBooking(Integer id, BookingDTO bookingDTO) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Booking not found"));

        booking.setTotalPrice(bookingDTO.getTotalPrice());
        booking.setBookingDate(bookingDTO.getBookingDate());
        booking.setBookingStatus(bookingDTO.getBookingStatus());
        booking.setStatus(bookingDTO.getStatus());
        // Assuming you have a method to fetch User by ID
        // booking.setUser(userService.getUserById(bookingDTO.getUserId()));

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
}