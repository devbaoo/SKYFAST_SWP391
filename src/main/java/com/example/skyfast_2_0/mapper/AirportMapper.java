package com.example.skyfast_2_0.mapper;

import com.example.skyfast_2_0.dto.AirportDTO;
import com.example.skyfast_2_0.entity.Airport;
import org.springframework.stereotype.Component;

@Component
public class AirportMapper {

    public AirportDTO toDTO(Airport airport) {
        AirportDTO dto = new AirportDTO();
        dto.setAirportId(airport.getAirportId());
        dto.setAirportCode(airport.getAirportCode());
        dto.setAirportName(airport.getAirportName());
        dto.setCountry(airport.getCountry());
        dto.setLocation(airport.getLocation());
        dto.setRunwaysCount(airport.getRunwaysCount());
        dto.setAirportType(airport.getAirportType());
        dto.setCapacity(airport.getCapacity());
        dto.setImage(airport.getImage());
        dto.setAirportStatus(airport.getAirportStatus());
        return dto;
    }

    public Airport toEntity(AirportDTO dto) {
        Airport airport = new Airport();
        airport.setAirportId(dto.getAirportId());
        airport.setAirportCode(dto.getAirportCode());
        airport.setAirportName(dto.getAirportName());
        airport.setCountry(dto.getCountry());
        airport.setLocation(dto.getLocation());
        airport.setRunwaysCount(dto.getRunwaysCount());
        airport.setAirportType(dto.getAirportType());
        airport.setCapacity(dto.getCapacity());
        airport.setImage(dto.getImage());
        airport.setAirportStatus(dto.getAirportStatus());
        return airport;
    }
}