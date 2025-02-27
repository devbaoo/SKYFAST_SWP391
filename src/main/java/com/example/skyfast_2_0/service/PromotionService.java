package com.example.skyfast_2_0.service;

import com.example.skyfast_2_0.dto.PromotionDTO;
import com.example.skyfast_2_0.mapper.PromotionMapper;
import com.example.skyfast_2_0.entity.Airline;
import com.example.skyfast_2_0.entity.Promotion;
import com.example.skyfast_2_0.repository.AirlineRepository;
import com.example.skyfast_2_0.repository.PromotionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PromotionService {

    @Autowired
    private PromotionRepository promotionRepository;

    @Autowired
    private AirlineRepository airlineRepository;

    @Autowired
    private PromotionMapper promotionMapper;

    public PromotionService(PromotionRepository promotionRepository, AirlineRepository airlineRepository) {
        this.promotionRepository = promotionRepository;
        this.airlineRepository = airlineRepository;
    }

    public List<PromotionDTO> getAllPromotions() {
        return promotionRepository.findAll().stream()
                .map(promotionMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    public PromotionDTO getPromotionById(Integer id) {
        Promotion promotion = promotionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Promotion not found"));
        return promotionMapper.convertToDTO(promotion);
    }

    public PromotionDTO updatePromotion(Integer id, PromotionDTO promotionDTO) {
        Promotion promotion = promotionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Promotion not found"));

        promotion.setCode(promotionDTO.getCode());
        promotion.setDescription(promotionDTO.getDescription());
        promotion.setDiscountPercentage(promotionDTO.getDiscountPercentage());
        promotion.setStartDate(promotionDTO.getStartDate());
        promotion.setEndDate(promotionDTO.getEndDate());
        promotion.setStatus(promotionDTO.getStatus());
        promotion.setImage(promotionDTO.getImage());
        promotion.setStatusPromotion(promotionDTO.getStatusPromotion());

        Airline airline = airlineRepository.findById(promotionDTO.getAirlineId())
                .orElseThrow(() -> new EntityNotFoundException("Airline not found"));
        promotion.setAirline(airline);

        promotionRepository.save(promotion);
        return promotionMapper.convertToDTO(promotion);
    }

    public PromotionDTO createPromotion(PromotionDTO promotionDTO) {
        Promotion promotion = new Promotion();
        promotion.setCode(promotionDTO.getCode());
        promotion.setDescription(promotionDTO.getDescription());
        promotion.setDiscountPercentage(promotionDTO.getDiscountPercentage());
        promotion.setStartDate(promotionDTO.getStartDate());
        promotion.setEndDate(promotionDTO.getEndDate());
        promotion.setStatus(promotionDTO.getStatus());
        promotion.setImage(promotionDTO.getImage());
        promotion.setStatusPromotion(promotionDTO.getStatusPromotion());

        Airline airline = airlineRepository.findById(promotionDTO.getAirlineId())
                .orElseThrow(() -> new EntityNotFoundException("Airline not found"));
        promotion.setAirline(airline);

        promotion = promotionRepository.save(promotion);
        return promotionMapper.convertToDTO(promotion);
    }

    public void softDeletePromotion(Integer id) {
        Promotion promotion = promotionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Promotion not found"));

        if ("ACTIVE".equals(promotion.getStatusPromotion())) {
            promotion.setStatusPromotion("INACTIVE");
            promotionRepository.save(promotion);
        } else {
            throw new IllegalStateException("Promotion is already inactive or in a non-deletable state");
        }
    }
}