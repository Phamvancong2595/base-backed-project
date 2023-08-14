package com.congpv.baseproject.core.domain;

import java.io.Serializable;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Product implements Serializable {
    private Long id;
    private String name;
    private Float price;
    private String author;
    private Integer number;
    private String imagePath;
}