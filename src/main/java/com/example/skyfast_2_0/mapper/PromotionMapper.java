package com.example.skyfast_2_0.mapper;

import com.example.skyfast_2_0.dto.PromotionDTO;
import com.example.skyfast_2_0.entity.Promotion;
import org.springframework.stereotype.Component;

@Component
public class PromotionMapper {
    public PromotionDTO convertToDTO(Promotion promotion) {
        PromotionDTO dto = new PromotionDTO();
        dto.setId(promotion.getId());
        dto.setCode(promotion.getCode());
        dto.setDescription(promotion.getDescription());
        dto.setDiscountPercentage(promotion.getDiscountPercentage());
        dto.setStartDate(promotion.getStartDate());
        dto.setEndDate(promotion.getEndDate());
        dto.setStatus(promotion.getStatus());
        dto.setImage(promotion.getImage());
        dto.setAirlineId(promotion.getAirline().getId());
        dto.setStatusPromotion(promotion.getStatusPromotion()); // Ensure this field is mapped
        return dto;
    }
}