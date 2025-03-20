package com.example.skyfast_2_0.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
public class FlightDTO {
    private Integer id;
    private String flightNumber;

    @NotNull(message = "Departure time is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Timestamp departureTime;

    @NotNull(message = "Arrival time is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Timestamp arrivalTime;

    private Integer duration;
    private Integer price;
    private String status;
    private Integer airlineId;
    private Integer airplaneId;
    private Integer routeId;
    private String statusFlight = "ACTIVE";
}