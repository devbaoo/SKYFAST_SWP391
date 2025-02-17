package com.example.skyfast_2_0.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// View Controller
@Controller
@RequestMapping("/users")
public class UserViewController {

    @GetMapping("/management")
    public String userManagement(Model model) {
        model.addAttribute("currentPage", "userlist.html");
        model.addAttribute("dashboardTitle", "SkyFast");
        return "userlist"; // Tên file Thymeleaf (ticketManagement.html)
    }
}
