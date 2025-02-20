package com.example.skyfast_2_0.dto;

import lombok.Data;

@Data
public class AirplaneDTO {
    private Integer id;
    private String airplaneName;
    private String manufacturer;
    private String diagram;
    private Integer speed;
    private Integer totalLength;
    private Integer wingspan;
    private Integer height;
    private String airplaneStatus;
    private Integer seatCapacity;
    private String airplaneImage;
    private Integer airlineId;
}