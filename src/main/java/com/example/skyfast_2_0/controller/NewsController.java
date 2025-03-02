package com.example.skyfast_2_0.controller;

import com.example.skyfast_2_0.dto.NewsDTO;
import com.example.skyfast_2_0.service.NewsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        model.addAttribute("newNews", new NewsDTO()); // Đảm bảo newNews có trong model
        return "newsManagement";
    }


    // Hiển thị chi tiết tin tức theo ID
    @GetMapping("/{id}")
    public String getNewsById(@PathVariable Integer id, Model model) {
        Optional<NewsDTO> newsOpt = newsService.getNewsById(id);
        if (newsOpt.isPresent()) {
            model.addAttribute("news", newsOpt.get());
            return "newsDetail"; // Trả về view newsDetail.html
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
    public String updateNews(@PathVariable Integer id, @ModelAttribute("news") NewsDTO newsDTO) {
        newsService.updateNews(id, newsDTO);
        return "redirect:/staff/newsManagement"; // Quay lại danh sách sau khi cập nhật
    }

    // Hiển thị form tạo mới tin tức
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("news", new NewsDTO());
        return "newsCreate"; // Trả về view newsCreate.html
    }

    // Tạo mới tin tức
    @PostMapping("/new")
    public String createNews(@ModelAttribute("news") NewsDTO newsDTO, Model model) {
        try {
            newsService.createNews(newsDTO);
            return "redirect:/staff/newsManagement";
        } catch (RuntimeException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "newsCreate";
        }
    }

    // Xử lý xóa (soft delete) tin tức
    @GetMapping("/delete/{id}")
    public String softDeleteNews(@PathVariable Integer id) {
        newsService.softDeleteNews(id);
        return "redirect:/staff/newsManagement"; // Quay lại danh sách sau khi xóa
    }
}
