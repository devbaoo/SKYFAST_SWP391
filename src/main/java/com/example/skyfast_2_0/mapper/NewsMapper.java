package com.example.skyfast_2_0.mapper;

import com.example.skyfast_2_0.dto.NewsDTO;
import com.example.skyfast_2_0.entity.News;
import org.springframework.stereotype.Controller;

@Controller
public class NewsMapper {
    public NewsDTO convertToDTO(News news) {
        NewsDTO dto = new NewsDTO();
        dto.setId(news.getId());
        dto.setTitle(news.getTitle());
        dto.setImage(news.getImage());
        dto.setContent(news.getContent());
        dto.setCategory(news.getCategory());
        dto.setStatus(news.getStatus());
        dto.setAirlineId(news.getArline().getId());
        return dto;
    }
}
