package com.example.skyfast_2_0.service;

import com.example.skyfast_2_0.dto.AirportDTO;
import com.example.skyfast_2_0.entity.Airport;
import com.example.skyfast_2_0.mapper.AirportMapper;
import com.example.skyfast_2_0.repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AirportService {

    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    private AirportMapper airportMapper;

    public List<AirportDTO> getAllAirports() {
        return airportRepository.findByAirportStatus("ACTIVE")
                .stream()
                .map(airportMapper::toDTO)
                .collect(Collectors.toList());
    }

    public AirportDTO getAirportById(Integer id) {
        Airport airport = airportRepository.findByAirportIdAndAirportStatus(id, "ACTIVE")
                .orElseThrow(() -> new RuntimeException("Airport not found"));
        return airportMapper.toDTO(airport);
    }

    public AirportDTO createAirport(AirportDTO airportDTO) {
        Airport airport = airportMapper.toEntity(airportDTO);
        airport.setAirportStatus("ACTIVE");
        Airport savedAirport = airportRepository.save(airport);
        return airportMapper.toDTO(savedAirport);
    }

    public AirportDTO updateAirport(Integer id, AirportDTO airportDTO) {
        Airport existingAirport = airportRepository.findByAirportIdAndAirportStatus(id, "ACTIVE")
                .orElseThrow(() -> new RuntimeException("Airport not found"));

        Airport updatedAirport = airportMapper.toEntity(airportDTO);
        updatedAirport.setAirportId(id);
        updatedAirport = airportRepository.save(updatedAirport);
        return airportMapper.toDTO(updatedAirport);
    }

    public void deleteAirport(Integer id) {
        Airport airport = airportRepository.findByAirportIdAndAirportStatus(id, "ACTIVE")
                .orElseThrow(() -> new RuntimeException("Airport not found"));
        airport.setAirportStatus("INACTIVE");
        airportRepository.save(airport);
    }
}