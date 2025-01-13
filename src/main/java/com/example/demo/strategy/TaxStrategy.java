package com.example.demo.strategy;

import com.example.demo.model.Product;
import java.math.BigDecimal;

public interface TaxStrategy {
    BigDecimal calculateTax(Product product);
}
