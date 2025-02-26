 package com.example.skyfast_2_0.service;

 import com.example.skyfast_2_0.dto.FlightDTO;
 import com.example.skyfast_2_0.entity.Airline;
 import com.example.skyfast_2_0.entity.Airplane;
 import com.example.skyfast_2_0.entity.Flight;
 import com.example.skyfast_2_0.entity.Route;
 import com.example.skyfast_2_0.mapper.FlightMapper;
 import com.example.skyfast_2_0.repository.AirlineRepository;
 import com.example.skyfast_2_0.repository.AirplaneRepository;
 import com.example.skyfast_2_0.repository.FlightRepository;
 import com.example.skyfast_2_0.repository.RouteRepository;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;

 import java.util.List;
 import java.util.stream.Collectors;

 @Service
 public class FlightService {

     @Autowired
     private FlightRepository flightRepository;

     @Autowired
     private AirlineRepository airlineRepository;

     @Autowired
     private AirplaneRepository airplaneRepository;

     @Autowired
     private RouteRepository routeRepository;

     @Autowired
     private FlightMapper flightMapper;

     public List<FlightDTO> getAllFlights() {
         return flightRepository.findAll()
                 .stream()
                 .map(flightMapper::toDTO)
                 .collect(Collectors.toList());
     }

     public FlightDTO getFlightById(Integer id) {
         Flight flight = flightRepository.findById(id)
                 .orElseThrow(() -> new RuntimeException("Flight not found"));
         return flightMapper.toDTO(flight);
     }

     public FlightDTO createFlight(FlightDTO flightDTO) {
         Flight flight = flightMapper.toEntity(flightDTO);

         // Fetch and set Airline
         Airline airline = airlineRepository.findById(flightDTO.getAirlineId())
                 .orElseThrow(() -> new RuntimeException("Airline not found"));
         flight.setAirline(airline);

         // Fetch and set Airplane
         Airplane airplane = airplaneRepository.findById(flightDTO.getAirplaneId())
                 .orElseThrow(() -> new RuntimeException("Airplane not found"));
         flight.setAirplane(airplane);

         // Fetch and set Route
         Route route = routeRepository.findById(flightDTO.getRouteId())
                 .orElseThrow(() -> new RuntimeException("Route not found"));
         flight.setRoute(route);

         flight.setStatusFlight("ACTIVE");
         Flight savedFlight = flightRepository.save(flight);
         return flightMapper.toDTO(savedFlight);
     }

     public FlightDTO updateFlight(Integer id, FlightDTO flightDTO) {
         Flight existingFlight = flightRepository.findById(id)
                 .orElseThrow(() -> new RuntimeException("Flight not found"));

         Flight updatedFlight = flightMapper.toEntity(flightDTO);
         updatedFlight.setId(id);

         // Fetch and set Airline
         Airline airline = airlineRepository.findById(flightDTO.getAirlineId())
                 .orElseThrow(() -> new RuntimeException("Airline not found"));
         updatedFlight.setAirline(airline);

         // Fetch and set Airplane
         Airplane airplane = airplaneRepository.findById(flightDTO.getAirplaneId())
                 .orElseThrow(() -> new RuntimeException("Airplane not found"));
         updatedFlight.setAirplane(airplane);

         // Fetch and set Route
         Route route = routeRepository.findById(flightDTO.getRouteId())
                 .orElseThrow(() -> new RuntimeException("Route not found"));
         updatedFlight.setRoute(route);

         updatedFlight = flightRepository.save(updatedFlight);
         return flightMapper.toDTO(updatedFlight);
     }

     public void deleteFlight(Integer id) {
         Flight flight = flightRepository.findById(id)
                 .orElseThrow(() -> new RuntimeException("Flight not found"));
         flight.setStatusFlight("INACTIVE");
         flightRepository.save(flight);
     }
 }