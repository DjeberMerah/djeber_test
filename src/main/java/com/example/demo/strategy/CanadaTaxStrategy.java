package com.example.demo.strategy;

import com.example.demo.model.Product;
import java.math.BigDecimal;

public class CanadaTaxStrategy implements TaxStrategy {
    @Override
    public BigDecimal calculateTax(Product product) {
        BigDecimal taxRate = new BigDecimal("0.15");
        return product.getPrice().multiply(taxRate);
    }
}
