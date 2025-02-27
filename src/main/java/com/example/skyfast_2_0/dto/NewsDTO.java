package com.example.skyfast_2_0.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewsDTO {
    private Integer id;
    private String title;
    private String image;
    private String content;
    private String category;
    private String status;
    private Integer airlineId;
}