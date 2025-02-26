 package com.example.skyfast_2_0.controller;

 import com.example.skyfast_2_0.dto.AirportDTO;
 import com.example.skyfast_2_0.service.AirportService;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.http.ResponseEntity;
 import org.springframework.web.bind.annotation.*;

 import java.util.List;

 @RestController
 @RequestMapping("/api/airports")
 public class AirportController {

     @Autowired
     private AirportService airportService;

     @GetMapping
     public ResponseEntity<List<AirportDTO>> getAllAirports() {
         return ResponseEntity.ok(airportService.getAllAirports());
     }

     @GetMapping("/{id}")
     public ResponseEntity<AirportDTO> getAirportById(@PathVariable Integer id) {
         return ResponseEntity.ok(airportService.getAirportById(id));
     }

     @PostMapping
     public ResponseEntity<AirportDTO> createAirport(@RequestBody AirportDTO airportDTO) {
         return ResponseEntity.ok(airportService.createAirport(airportDTO));
     }

     @PutMapping("/{id}")
     public ResponseEntity<AirportDTO> updateAirport(@PathVariable Integer id,
                                                     @RequestBody AirportDTO airportDTO) {
         return ResponseEntity.ok(airportService.updateAirport(id, airportDTO));
     }

     @DeleteMapping("/{id}")
     public ResponseEntity<Void> deleteAirport(@PathVariable Integer id) {
         airportService.deleteAirport(id);
         return ResponseEntity.ok().build();
     }
 }