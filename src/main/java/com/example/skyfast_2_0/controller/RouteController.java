 package com.example.skyfast_2_0.controller;

 import com.example.skyfast_2_0.dto.RouteDTO;
 import com.example.skyfast_2_0.service.RouteService;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.http.ResponseEntity;
 import org.springframework.web.bind.annotation.*;

 import java.util.List;

 @RestController
 @RequestMapping("/api/routes")
 public class RouteController {

     @Autowired
     private RouteService routeService;

     @GetMapping
     public ResponseEntity<List<RouteDTO>> getAllRoutes() {
         return ResponseEntity.ok(routeService.getAllRoutes());
     }

     @GetMapping("/{id}")
     public ResponseEntity<RouteDTO> getRouteById(@PathVariable Integer id) {
         return ResponseEntity.ok(routeService.getRouteById(id));
     }

     @PostMapping
     public ResponseEntity<RouteDTO> createRoute(@RequestBody RouteDTO routeDTO) {
         return ResponseEntity.ok(routeService.createRoute(routeDTO));
     }

     @PutMapping("/{id}")
     public ResponseEntity<RouteDTO> updateRoute(@PathVariable Integer id,
                                                 @RequestBody RouteDTO routeDTO) {
         return ResponseEntity.ok(routeService.updateRoute(id, routeDTO));
     }

     @DeleteMapping("/{id}")
     public ResponseEntity<Void> deleteRoute(@PathVariable Integer id) {
         routeService.deleteRoute(id);
         return ResponseEntity.ok().build();
     }
 }