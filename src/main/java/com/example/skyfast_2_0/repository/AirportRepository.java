package com.example.skyfast_2_0.repository;

import com.example.skyfast_2_0.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Integer> {
    List<Airport> findByAirportStatus(String airportStatus);
    Optional<Airport> findByAirportIdAndAirportStatus(Integer id, String airportStatus);
}