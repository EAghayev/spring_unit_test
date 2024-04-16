package com.testingapp.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ProductGetDto {
    private Long id;
    private String name;
    private Double price;
    private LocalDateTime expireDate;
}
