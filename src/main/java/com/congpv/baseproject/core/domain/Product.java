package com.congpv.baseproject.core.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Product {
    private Long id;
    private String name;
    private Float price;
    private String author;
    private Integer number;
    private String imagePath;
}