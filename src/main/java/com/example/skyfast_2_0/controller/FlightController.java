package com.example.skyfast_2_0.controller;

import com.example.skyfast_2_0.dto.FlightDTO;
import com.example.skyfast_2_0.service.FlightService;
import com.example.skyfast_2_0.service.AirlineService;
import com.example.skyfast_2_0.service.AirplaneService;
import com.example.skyfast_2_0.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @Autowired
    private AirlineService airlineService;

    @Autowired
    private AirplaneService airplaneService;

    @Autowired
    private RouteService routeService;

    @GetMapping
    public String getAllFlights(Model model) {
        model.addAttribute("flights", flightService.getAllFlights());
        model.addAttribute("flight", new FlightDTO());
        model.addAttribute("airlines", airlineService.getAllAirlines());
        model.addAttribute("airplanes", airplaneService.getAllAirplanes());
        model.addAttribute("routes", routeService.getAllRoutes());
        return "flightManagement";
    }

    @GetMapping("/{id}")
    public String getFlightById(@PathVariable Integer id, Model model) {
        model.addAttribute("flight", flightService.getFlightById(id));
        model.addAttribute("airlines", airlineService.getAllAirlines());
        model.addAttribute("airplanes", airplaneService.getAllAirplanes());
        model.addAttribute("routes", routeService.getAllRoutes());
        return "flightDetail";
    }

    @PostMapping
    public String createFlight(@ModelAttribute FlightDTO flightDTO) {
        flightService.createFlight(flightDTO);
        return "redirect:/flights";
    }

    @PostMapping("/{id}")
    public String updateFlight(@PathVariable Integer id, @ModelAttribute FlightDTO flightDTO) {
        flightService.updateFlight(id, flightDTO);
        return "redirect:/flights";
    }

    @PostMapping("/{id}/delete")
    public String deleteFlight(@PathVariable Integer id) {
        flightService.deleteFlight(id);
        return "redirect:/flights";
    }
}