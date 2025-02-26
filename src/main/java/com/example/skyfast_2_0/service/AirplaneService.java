 package com.example.skyfast_2_0.service;

 import com.example.skyfast_2_0.dto.AirplaneDTO;
 import com.example.skyfast_2_0.entity.Airline;
 import com.example.skyfast_2_0.entity.Airplane;
 import com.example.skyfast_2_0.mapper.AirplaneMapper;
 import com.example.skyfast_2_0.repository.AirlineRepository;
 import com.example.skyfast_2_0.repository.AirplaneRepository;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;

 import java.util.List;
 import java.util.stream.Collectors;

 @Service
 public class AirplaneService {

     @Autowired
     private AirplaneRepository airplaneRepository;

     @Autowired
     private AirlineRepository airlineRepository;

     @Autowired
     private AirplaneMapper airplaneMapper;

     public List<AirplaneDTO> getAllAirplanes() {
         return airplaneRepository.findByAirplaneStatus("ACTIVE")
                 .stream()
                 .map(airplaneMapper::toDTO)
                 .collect(Collectors.toList());
     }

     public AirplaneDTO getAirplaneById(Integer id) {
         Airplane airplane = airplaneRepository.findByIdAndAirplaneStatus(id, "ACTIVE")
                 .orElseThrow(() -> new RuntimeException("Airplane not found"));
         return airplaneMapper.toDTO(airplane);
     }

     public AirplaneDTO createAirplane(AirplaneDTO airplaneDTO) {
         Airplane airplane = airplaneMapper.toEntity(airplaneDTO);

         // Fetch and set Airline
         Airline airline = airlineRepository.findById(airplaneDTO.getAirlineId())
                 .orElseThrow(() -> new RuntimeException("Airline not found"));
         airplane.setAirline(airline);

         airplane.setAirplaneStatus("ACTIVE");
         Airplane savedAirplane = airplaneRepository.save(airplane);
         return airplaneMapper.toDTO(savedAirplane);
     }

     public AirplaneDTO updateAirplane(Integer id, AirplaneDTO airplaneDTO) {
         Airplane existingAirplane = airplaneRepository.findByIdAndAirplaneStatus(id, "ACTIVE")
                 .orElseThrow(() -> new RuntimeException("Airplane not found"));

         Airplane updatedAirplane = airplaneMapper.toEntity(airplaneDTO);
         updatedAirplane.setId(id);

         // Fetch and set Airline
         Airline airline = airlineRepository.findById(airplaneDTO.getAirlineId())
                 .orElseThrow(() -> new RuntimeException("Airline not found"));
         updatedAirplane.setAirline(airline);

         updatedAirplane = airplaneRepository.save(updatedAirplane);
         return airplaneMapper.toDTO(updatedAirplane);
     }

     public void deleteAirplane(Integer id) {
         Airplane airplane = airplaneRepository.findByIdAndAirplaneStatus(id, "ACTIVE")
                 .orElseThrow(() -> new RuntimeException("Airplane not found"));
         airplane.setAirplaneStatus("INACTIVE");
         airplaneRepository.save(airplane);
     }
 }