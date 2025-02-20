package com.example.skyfast_2_0.service;

import com.example.skyfast_2_0.dto.RouteDTO;
import com.example.skyfast_2_0.entity.Airport;
import com.example.skyfast_2_0.entity.Route;
import com.example.skyfast_2_0.mapper.RouteMapper;
import com.example.skyfast_2_0.repository.AirportRepository;
import com.example.skyfast_2_0.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RouteService {

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    private RouteMapper routeMapper;

    public List<RouteDTO> getAllRoutes() {
        return routeRepository.findByRouteStatus("ACTIVE")
                .stream()
                .map(routeMapper::toDTO)
                .collect(Collectors.toList());
    }

    public RouteDTO getRouteById(Integer id) {
        Route route = routeRepository.findByIdAndRouteStatus(id, "ACTIVE")
                .orElseThrow(() -> new RuntimeException("Route not found"));
        return routeMapper.toDTO(route);
    }

    public RouteDTO createRoute(RouteDTO routeDTO) {
        Route route = routeMapper.toEntity(routeDTO);

        Airport departureAirport = airportRepository.findById(routeDTO.getDepartureAirportId())
                .orElseThrow(() -> new RuntimeException("Departure Airport not found"));
        Airport arrivalAirport = airportRepository.findById(routeDTO.getArrivalAirportId())
                .orElseThrow(() -> new RuntimeException("Arrival Airport not found"));

        route.setDepartureAirport(departureAirport);
        route.setArrivalAirport(arrivalAirport);
        route.setRouteStatus("ACTIVE");

        Route savedRoute = routeRepository.save(route);
        return routeMapper.toDTO(savedRoute);
    }

    public RouteDTO updateRoute(Integer id, RouteDTO routeDTO) {
        Route existingRoute = routeRepository.findByIdAndRouteStatus(id, "ACTIVE")
                .orElseThrow(() -> new RuntimeException("Route not found"));

        Route updatedRoute = routeMapper.toEntity(routeDTO);
        updatedRoute.setId(id);

        Airport departureAirport = airportRepository.findById(routeDTO.getDepartureAirportId())
                .orElseThrow(() -> new RuntimeException("Departure Airport not found"));
        Airport arrivalAirport = airportRepository.findById(routeDTO.getArrivalAirportId())
                .orElseThrow(() -> new RuntimeException("Arrival Airport not found"));

        updatedRoute.setDepartureAirport(departureAirport);
        updatedRoute.setArrivalAirport(arrivalAirport);

        updatedRoute = routeRepository.save(updatedRoute);
        return routeMapper.toDTO(updatedRoute);
    }

    public void deleteRoute(Integer id) {
        Route route = routeRepository.findByIdAndRouteStatus(id, "ACTIVE")
                .orElseThrow(() -> new RuntimeException("Route not found"));
        route.setRouteStatus("INACTIVE");
        routeRepository.save(route);
    }
}