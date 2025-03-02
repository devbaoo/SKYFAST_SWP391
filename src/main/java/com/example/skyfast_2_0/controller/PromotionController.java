package com.example.skyfast_2_0.controller;

import com.example.skyfast_2_0.dto.PromotionDTO;
import com.example.skyfast_2_0.service.PromotionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/staff/promotionManagement")
public class PromotionController {

    private final PromotionService promotionService;

    public PromotionController(PromotionService promotionService) {
        this.promotionService = promotionService;
    }

    // Hiển thị danh sách tất cả các khuyến mãi
    @GetMapping
    public String getAllPromotions(Model model) {
        List<PromotionDTO> promotions = promotionService.getAllPromotions();
        model.addAttribute("promotions", promotions);
        return "promotionManagement"; // Trả về view promotionManagement.html
    }

    // Hiển thị chi tiết của 1 khuyến mãi theo ID
    @GetMapping("/{id}")
    public String getPromotionById(@PathVariable Integer id, Model model) {
        PromotionDTO promotion = promotionService.getPromotionById(id);
        model.addAttribute("promotion", promotion);
        return "promotionDetail"; // Trả về view promotionDetail.html
    }

    // Hiển thị form chỉnh sửa khuyến mãi
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        PromotionDTO promotion = promotionService.getPromotionById(id);
        model.addAttribute("promotion", promotion);
        return "promotionEdit"; // Trả về view promotionEdit.html
    }

    // Cập nhật khuyến mãi
    @PostMapping("/edit/{id}")
    public String updatePromotion(@PathVariable Integer id, @ModelAttribute("promotion") PromotionDTO promotionDTO) {
        promotionService.updatePromotion(id, promotionDTO);
        return "redirect:/staff/promotionManagement"; // Quay lại danh sách sau khi cập nhật
    }

    // Hiển thị form tạo mới khuyến mãi
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("promotion", new PromotionDTO());
        return "promotionCreate"; // Trả về view promotionCreate.html
    }

    // Tạo mới khuyến mãi
    @PostMapping("/new")
    public String createPromotion(@ModelAttribute("promotion") PromotionDTO promotionDTO) {
        promotionService.createPromotion(promotionDTO);
        return "redirect:/staff/promotionManagement"; // Sau khi tạo mới, quay lại danh sách
    }

    // Xử lý xóa (soft delete) khuyến mãi
    @GetMapping("/delete/{id}")
    public String softDeletePromotion(@PathVariable Integer id) {
        promotionService.softDeletePromotion(id);
        return "redirect:/staff/promotionManagement"; // Quay lại danh sách sau khi xóa
    }
}
