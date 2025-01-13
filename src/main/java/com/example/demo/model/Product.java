package com.example.demo.model;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class Product {
    private Long id;
    private String name;
    private BigDecimal price;
    private Country country;

    public Product(Long id, String name, BigDecimal price, Country country) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.country = country;
    }
}
