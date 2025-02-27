package com.example.skyfast_2_0.service;

import com.example.skyfast_2_0.dto.NewsDTO;
import com.example.skyfast_2_0.entity.Airline;
import com.example.skyfast_2_0.entity.News;
import com.example.skyfast_2_0.mapper.NewsMapper;
import com.example.skyfast_2_0.repository.AirlineRepository;
import com.example.skyfast_2_0.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NewsService {

    @Autowired
    private NewsMapper newsMapper;

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private AirlineRepository airlineRepository;




    public NewsService(NewsRepository newsRepository, AirlineRepository airlineRepository) {
        this.newsRepository = newsRepository;
        this.airlineRepository = airlineRepository;
    }

    public List<NewsDTO> getAllNews() {
        return newsRepository.findAll().stream()
                .map(newsMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<NewsDTO> getNewsById(Integer id) {
        return newsRepository.findById(id).map(newsMapper::convertToDTO);
    }

    public Optional<NewsDTO> updateNews(Integer id, NewsDTO newsDTO) {
        return newsRepository.findById(id).map(news -> {
            news.setTitle(newsDTO.getTitle());
            news.setImage(newsDTO.getImage());
            news.setContent(newsDTO.getContent());
            news.setCategory(newsDTO.getCategory());
            news.setStatus(newsDTO.getStatus());

            Airline airline = airlineRepository.findById(newsDTO.getAirlineId())
                    .orElseThrow(() -> new RuntimeException("Airline not found"));
            news.setArline(airline);

            newsRepository.save(news);
            return newsMapper.convertToDTO(news);
        });
    }

    public NewsDTO createNews(NewsDTO newsDTO) {
        News news = new News();
        news.setTitle(newsDTO.getTitle());
        news.setImage(newsDTO.getImage());
        news.setContent(newsDTO.getContent());
        news.setCategory(newsDTO.getCategory());
        news.setStatus(newsDTO.getStatus());

        Airline airline = airlineRepository.findById(newsDTO.getAirlineId())
                .orElseThrow(() -> new RuntimeException("Airline not found"));
        news.setArline(airline);

        News savedNews = newsRepository.save(news);
        return newsMapper.convertToDTO(savedNews);
    }

    public boolean softDeleteNews(Integer id) {
        return newsRepository.findById(id).map(news -> {
            news.setStatus("INACTIVE");
            newsRepository.save(news);
            return true;
        }).orElse(false);
    }

}