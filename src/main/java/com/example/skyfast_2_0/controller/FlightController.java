package com.example.skyfast_2_0.controller;

import com.example.skyfast_2_0.config.TimestampPropertyEditor;
import com.example.skyfast_2_0.dto.FlightDTO;
import com.example.skyfast_2_0.service.FlightService;
import com.example.skyfast_2_0.service.AirlineService;
import com.example.skyfast_2_0.service.AirplaneService;
import com.example.skyfast_2_0.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

import java.sql.Timestamp;

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

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Timestamp.class, new TimestampPropertyEditor());
    }

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
    public String createFlight(
            @Valid @ModelAttribute FlightDTO flightDTO,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            System.out.println("Binding errors: " + bindingResult.getAllErrors());
            model.addAttribute("flight", flightDTO);
            model.addAttribute("airlines", airlineService.getAllAirlines());
            model.addAttribute("airplanes", airplaneService.getAllAirplanes());
            model.addAttribute("routes", routeService.getAllRoutes());
            return "flightManagement"; // Trả về form nếu có lỗi
        }

        try {
            System.out.println("Creating flight with DTO: " + flightDTO);
            flightService.createFlight(flightDTO);
            redirectAttributes.addFlashAttribute("successMessage", "Flight created successfully!");
        } catch (Exception e) {
            System.out.println("Error creating flight: " + e.getMessage());
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to create flight: " + e.getMessage());
        }
        return "redirect:/flights";
    }

    @PostMapping("/{id}")
    public String updateFlight(
            @PathVariable Integer id,
            @Valid @ModelAttribute("flight") FlightDTO flightDTO,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            System.out.println("Binding errors: " + bindingResult.getAllErrors());
            model.addAttribute("flight", flightDTO);
            model.addAttribute("airlines", airlineService.getAllAirlines());
            model.addAttribute("airplanes", airplaneService.getAllAirplanes());
            model.addAttribute("routes", routeService.getAllRoutes());
            return "flightDetail";
        }

        try {
            System.out.println("Updating flight with DTO: " + flightDTO);
            flightService.updateFlight(id, flightDTO);
            redirectAttributes.addFlashAttribute("successMessage", "Flight updated successfully!");
        } catch (Exception e) {
            System.out.println("Error updating flight: " + e.getMessage());
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to update flight: " + e.getMessage());
        }
        return "redirect:/flights";
    }

    @PostMapping("/{id}/delete")
    public String deleteFlight(@PathVariable Integer id) {
        flightService.deleteFlight(id);
        return "redirect:/flights";
    }
}