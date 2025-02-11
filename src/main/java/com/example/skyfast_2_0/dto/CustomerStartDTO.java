package com.example.skyfast_2_0.dto;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor

public class CustomerStartDTO {
    private long newlyRegistered;
    private long newlyBought;
}
