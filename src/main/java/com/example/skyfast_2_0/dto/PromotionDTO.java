package com.example.skyfast_2_0.dto;

import java.time.LocalDate;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class PromotionDTO {
    private Integer id;
    private String code;
    private String description;
    private Integer discountPercentage;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    private String image;
    private Integer airlineId;
    private String statusPromotion;


}