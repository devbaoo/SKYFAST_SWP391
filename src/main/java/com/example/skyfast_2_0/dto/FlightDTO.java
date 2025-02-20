package com.example.skyfast_2_0.dto;

import lombok.Data;
import java.util.Date;

@Data
public class FlightDTO {
    private Integer id;
    private String flightNumber;
    private Date departureTime;
    private Date arrivalTime;
    private Integer duration;
    private String status;
    private Integer airlineId;
    private Integer airplaneId;
    private Integer routeId;
    private String statusFlight = "ACTIVE"; // Thêm trường này để quản lý soft delete
}