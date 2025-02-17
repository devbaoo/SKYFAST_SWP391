package com.example.skyfast_2_0.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// View Controller
@Controller
@RequestMapping("/tickets")
public class TicketViewController {

    @GetMapping("/management")
    public String ticketManagement(Model model) {
        model.addAttribute("currentPage", "ticketManagement.html");
        model.addAttribute("dashboardTitle", "SkyFast");
        // Các thuộc tính khác nếu cần
        return "ticketManagement"; // Tên file Thymeleaf (ticketManagement.html)
    }
    @GetMapping("/update/{id}")
    public String ticketUpdate(Model model) {
        model.addAttribute("currentPage", "ticketDetail.html");
        model.addAttribute("dashboardTitle", "SkyFast");
        // Các thuộc tính khác nếu cần
        return "ticketDetail"; // Tên file Thymeleaf (ticketManagement.html)
    }
}
