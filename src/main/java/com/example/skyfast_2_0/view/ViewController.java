package com.example.skyfast_2_0.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// View Controller
@Controller
public class ViewController {


    @GetMapping("/users/management")
    public String userManagement(Model model) {
        model.addAttribute("currentPage", "userlist");
        return "userlist";
    }

    @GetMapping("/tickets/management")
    public String ticketManagement(Model model) {
        model.addAttribute("currentPage", "ticketManagement");
        return "ticketManagement";
    }

    @GetMapping("/airline/management")
    public String airlineManagement(Model model) {
        model.addAttribute("currentPage", "airlineManagement");
        return "airlineManagement";
    }

    @GetMapping("/flight/management")
    public String flightManagement(Model model) {
        model.addAttribute("currentPage", "flightManagement");
        return "flightManagement";
    }

    @GetMapping("/booking/management")
    public String bookingManagement(Model model) {
        model.addAttribute("currentPage", "bookingManagement");
        return "bookingManagement";
    }

    @GetMapping("/promotion/management")
    public String promotionManagement(Model model) {
        model.addAttribute("currentPage", "promotionManagement");
        return "promotionManagement";
    }

}