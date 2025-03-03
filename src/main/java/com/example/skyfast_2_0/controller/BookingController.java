package com.example.skyfast_2_0.controller;

import com.example.skyfast_2_0.dto.BookingDTO;
import com.example.skyfast_2_0.dto.TicketDTO;
import com.example.skyfast_2_0.service.BookingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/staff/bookingManagement")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    // Hiển thị tất cả các booking
    @GetMapping
    public String getAllBookings(Model model) {
        List<BookingDTO> bookings = bookingService.getAllBookings();
        model.addAttribute("bookings", bookings);
        model.addAttribute("pageTitle", "Booking Management");
        return "bookingManagement"; // Trả về view bookingManagement.html
    }

    // Hiển thị chi tiết booking theo ID
    @GetMapping("/{id}")
    public String getBookingById(@PathVariable Integer id, Model model) {
        BookingDTO booking = bookingService.getBookingById(id);
        model.addAttribute("booking", booking);
        return "bookingDetail"; // Trả về view bookingDetail.html
    }

    // Hiển thị form chỉnh sửa booking
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        BookingDTO booking = bookingService.getBookingById(id);
        model.addAttribute("booking", booking);
        return "bookingEdit"; // Trả về view bookingEdit.html
    }

    // Cập nhật booking
    @PostMapping("/edit/{id}")
    @ResponseBody
    public BookingDTO updateBooking(@PathVariable Integer id) {
        BookingDTO booking = bookingService.getBookingById(id);
        String newStatus = "ACTIVE".equals(booking.getStatus()) ? "INACTIVE" : "ACTIVE";
        return bookingService.updateBookingStatus(id, newStatus);
    }

    @GetMapping("/{id}/tickets")
    @ResponseBody
    public List<TicketDTO> getTicketsByBookingId(@PathVariable Integer id) {
        return bookingService.getTicketsByBookingId(id);
    }

    // Xử lý xóa (soft delete) booking
    @GetMapping("/delete/{id}")
    public String softDeleteBooking(@PathVariable Integer id) {
        bookingService.softDeleteBooking(id);
        return "redirect:/staff/bookingManagement"; // Quay lại danh sách Booking sau khi xóa
    }
}
