package com.example.skyfast_2_0.repository;

import com.example.skyfast_2_0.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {
    List<Flight> findByStatusFlight(String statusFlight);
}