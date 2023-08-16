package com.congpv.baseproject.core.domain;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {
    private Long id;
    private String name;
    private Float price;
    private String author;
    private Integer number;
    private String imagePath;
}