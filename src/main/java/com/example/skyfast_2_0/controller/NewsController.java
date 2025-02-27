package com.example.skyfast_2_0.controller;

import com.example.skyfast_2_0.dto.NewsDTO;
import com.example.skyfast_2_0.service.NewsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/news")
public class NewsController {

    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping
    public List<NewsDTO> getAllNews() {
        return newsService.getAllNews();
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsDTO> getNewsById(@PathVariable Integer id) {
        Optional<NewsDTO> newsDTO = newsService.getNewsById(id);
        return newsDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<NewsDTO> updateNews(@PathVariable Integer id, @RequestBody NewsDTO newsDTO) {
        Optional<NewsDTO> updatedNews = newsService.updateNews(id, newsDTO);
        return updatedNews.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> softDeleteNews(@PathVariable Integer id) {
        boolean deleted = newsService.softDeleteNews(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<NewsDTO> createNews(@RequestBody NewsDTO newsDTO) {
        try {
            NewsDTO createdNews = newsService.createNews(newsDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdNews);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}