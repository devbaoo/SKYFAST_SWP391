package com.example.skyfast_2_0.mapper;

import com.example.skyfast_2_0.dto.RouteDTO;
import com.example.skyfast_2_0.entity.Route;
import org.springframework.stereotype.Component;

@Component
public class RouteMapper {
    
    public RouteDTO toDTO(Route route) {
        RouteDTO dto = new RouteDTO();
        dto.setId(route.getId());
        // Sửa lại cách lấy ID của airport
        dto.setDepartureAirportId(route.getDepartureAirport().getAirportId());
        dto.setArrivalAirportId(route.getArrivalAirport().getAirportId());
        dto.setDistance(route.getDistance());
        dto.setRouteStatus(route.getRouteStatus());
        return dto;
    }

    public Route toEntity(RouteDTO dto) {
        Route route = new Route();
        route.setId(dto.getId());
        route.setDistance(dto.getDistance());
        route.setRouteStatus(dto.getRouteStatus());
        // Không set Airport ở đây, sẽ được set trong Service
        return route;
    }
}