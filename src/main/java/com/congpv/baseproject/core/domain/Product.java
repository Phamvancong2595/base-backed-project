package com.congpv.baseproject.core.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Product {
    private Integer id;
    private String name;
    private Float price;
}