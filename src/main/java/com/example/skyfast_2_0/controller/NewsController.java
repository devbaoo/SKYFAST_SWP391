package com.example.skyfast_2_0.controller;

import com.example.skyfast_2_0.dto.NewsDTO;
import com.example.skyfast_2_0.service.NewsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/staff/newsManagement")
public class NewsController {

    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    // Hiển thị danh sách tất cả các tin tức
    @GetMapping
    public String getAllNews(Model model) {
        List<NewsDTO> newsList = newsService.getAllNews();
        model.addAttribute("newsList", newsList);
        model.addAttribute("newNews", new NewsDTO());
        model.addAttribute("pageTitle", "News Management");
        return "newsManagement";
    }

    // Hiển thị chi tiết tin tức theo ID
    @GetMapping("/{id}")
    public String getNewsById(@PathVariable Integer id, Model model) {
        Optional<NewsDTO> newsOpt = newsService.getNewsById(id);
        if (newsOpt.isPresent()) {
            model.addAttribute("news", newsOpt.get());
            model.addAttribute("pageTitle", "Edit News");
            return "newsDetail";
        }
        return "redirect:/staff/newsManagement";
    }

    // Hiển thị form chỉnh sửa tin tức
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        Optional<NewsDTO> newsOpt = newsService.getNewsById(id);
        if (newsOpt.isPresent()) {
            model.addAttribute("news", newsOpt.get());
            return "newsEdit"; // Trả về view newsEdit.html
        }
        return "redirect:/staff/newsManagement";
    }

    // Cập nhật tin tức
    @PostMapping("/edit/{id}")
    public String updateNews(@PathVariable Integer id, @ModelAttribute NewsDTO newsDTO, RedirectAttributes redirectAttributes) {
        try {
            Optional<NewsDTO> updated = newsService.updateNews(id, newsDTO);
            if (updated.isPresent()) {
                redirectAttributes.addFlashAttribute("successMessage", "News updated successfully!");
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "News not found!");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to update news: " + e.getMessage());
        }
        return "redirect:/staff/newsManagement";
    }

    // Hiển thị form tạo mới tin tức
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("news", new NewsDTO());
        return "newsCreate"; // Trả về view newsCreate.html
    }

    // Tạo mới tin tức
    @PostMapping("/new")
    public String createNews(@ModelAttribute("newNews") NewsDTO newsDTO, RedirectAttributes redirectAttributes) {
        try {
            newsService.createNews(newsDTO);
            redirectAttributes.addFlashAttribute("successMessage", "News created successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to create news: " + e.getMessage());
        }
        return "redirect:/staff/newsManagement";
    }

    // Xử lý xóa (soft delete) tin tức
    @PostMapping("/delete/{id}")
    public String softDeleteNews(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            if (newsService.softDeleteNews(id)) {
                redirectAttributes.addFlashAttribute("successMessage", "News deactivated successfully!");
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "News not found!");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to deactivate news: " + e.getMessage());
        }
        return "redirect:/staff/newsManagement";
    }
}
