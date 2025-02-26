package com.example.skyfast_2_0.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class BookingDTO {
    // Getters and Setters
    private Integer id;
    private Float totalPrice;
    private LocalDate bookingDate;
    private String bookingStatus;
    private Integer userId;
    private String status;

    // Constructor with parameters
    public BookingDTO(Integer id, Float totalPrice, LocalDate bookingDate, String bookingStatus, Integer userId, String status) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.bookingDate = bookingDate;
        this.bookingStatus = bookingStatus;
        this.userId = userId;
        this.status = status;
    }

    // Default constructor
    public BookingDTO() {
    }

}