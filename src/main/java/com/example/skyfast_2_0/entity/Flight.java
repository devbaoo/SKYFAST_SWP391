package com.example.skyfast_2_0.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "flight")
public class Flight {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Column(name = "flight_number", nullable = false)
    private String flightNumber;

    @NotNull(message = "Departure time is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Column(name = "departure_time", nullable = false)
    private Timestamp departureTime;

    @NotNull(message = "Arrival time is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Column(name = "arrival_time", nullable = false)
    private Timestamp arrivalTime;

    @NotNull
    @Column(name = "duration", nullable = false)
    private Integer duration;

    @Size(max = 255)
    @NotNull
    @Column(name = "status", nullable = false)
    private String status;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "airline_id", nullable = false)
    private Airline airline;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "airplane_id", nullable = false)
    private Airplane airplane;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "route_id", nullable = false)
    private Route route;

    @NotNull
    @Column(name = "price", nullable = false)
    private Integer price;

    @NotNull
    @Column(name = "status_flight", nullable = false)
    private String statusFlight = "ACTIVE";

}