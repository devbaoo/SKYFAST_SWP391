package com.example.skyfast_2_0.controller;

import com.example.skyfast_2_0.dto.TicketDTO;
import com.example.skyfast_2_0.dto.TicketInfoDTO;
import com.example.skyfast_2_0.service.TicketService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/staff/ticketManagement")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    // Hiển thị danh sách ticket (sử dụng ticketManagement.html)
    @GetMapping
    public String getAllTickets(Model model) {
        List<TicketInfoDTO> tickets = ticketService.getAllTickets();
        model.addAttribute("tickets", tickets);
        // "ticketManagement" tương ứng với file ticketManagement.html trong thư mục templates
        return "ticketManagement";
    }

    // Hiển thị chi tiết ticket (dùng ticketDetail.html)
    @GetMapping("/{id}")
    public String getTicketById(@PathVariable Integer id, Model model) {
        TicketInfoDTO ticket = ticketService.getTicketById(id);
        if (ticket == null) {
            // Bạn có thể xử lý thêm thông báo lỗi nếu cần
            return "ticketDetail";
        }
        model.addAttribute("ticketDTO", ticket);
        // "ticketDetail" tương ứng với file ticketDetail.html trong thư mục templates
        return "ticketDetail";
    }

    // Xử lý cập nhật ticket (POST từ form trong ticketDetail.html)
    @PostMapping("/{id}")
    public String updateTicket(@PathVariable Integer id,
                               @ModelAttribute("ticketDTO") TicketDTO ticketDTO,
                               Model model) {
        TicketInfoDTO updatedTicket = ticketService.updateTicket(id, ticketDTO);
        if (updatedTicket == null) {
            // Nếu update thất bại, hiển thị lại trang chi tiết với thông báo lỗi (nếu cần)
            return "ticketDetail";
        }
        // Sau khi cập nhật thành công, chuyển hướng về trang danh sách ticket
        return "redirect:/staff/ticketManagement";
    }
}
