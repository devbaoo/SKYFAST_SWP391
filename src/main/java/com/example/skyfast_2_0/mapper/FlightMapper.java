package com.example.skyfast_2_0.mapper;

import com.example.skyfast_2_0.dto.FlightDTO;
import com.example.skyfast_2_0.entity.Flight;
import org.springframework.stereotype.Component;

@Component
public class FlightMapper {

    public FlightDTO toDTO(Flight flight) {
        FlightDTO dto = new FlightDTO();
        dto.setId(flight.getId());
        dto.setFlightNumber(flight.getFlightNumber());
        dto.setDepartureTime(flight.getDepartureTime());
        dto.setArrivalTime(flight.getArrivalTime());
        dto.setDuration(flight.getDuration());
        dto.setStatus(flight.getStatus());
        dto.setAirlineId(flight.getAirline().getId());
        dto.setAirplaneId(flight.getAirplane().getId());
        dto.setRouteId(flight.getRoute().getId());
        dto.setStatusFlight(flight.getStatusFlight());
        return dto;
    }

    public Flight toEntity(FlightDTO dto) {
        Flight flight = new Flight();
        flight.setId(dto.getId());
        flight.setFlightNumber(dto.getFlightNumber());
        flight.setDepartureTime(dto.getDepartureTime());
        flight.setArrivalTime(dto.getArrivalTime());
        flight.setDuration(dto.getDuration());
        flight.setStatus(dto.getStatus());
        flight.setStatusFlight(dto.getStatusFlight());
        // Không set các relationship ở đây, sẽ được set trong Service
        return flight;
    }
}