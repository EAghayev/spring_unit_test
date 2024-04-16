package com.testingapp.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductCreateDto {
    private String name;
    private Double price;
}
