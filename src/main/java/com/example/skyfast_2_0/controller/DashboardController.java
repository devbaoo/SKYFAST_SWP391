package com.example.skyfast_2_0.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String dashboard() {
        return "Dashboard";
    }

    @GetMapping("/userlist")
    public String userList() {
        return "userlist";
    }

    @GetMapping("/airplanelist")
    public String airplaneList() {
        return "airplanelist";
    }

    @GetMapping("/ticketManagement")
    public String ticketManagement() {
        return "ticketManagement";
    }

    @GetMapping("/orderManagement")
    public String orderManagement() {
        return "orderManagement";
    }
}