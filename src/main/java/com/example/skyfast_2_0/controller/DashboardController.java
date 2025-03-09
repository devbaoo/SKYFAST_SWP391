package com.example.skyfast_2_0.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {
    @GetMapping
    public String getDashboard(Model model) {
        model.addAttribute("pageTitle", "Dashboard Management");
        return "Dashboard"; // Trả về trang Thymeleaf
    }
}