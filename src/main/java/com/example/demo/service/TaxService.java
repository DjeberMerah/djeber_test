package com.example.demo.service;


import com.example.demo.model.Country;
import com.example.demo.model.Product;
import com.example.demo.strategy.CanadaTaxStrategy;
import com.example.demo.strategy.FranceTaxStrategy;
import com.example.demo.strategy.TaxStrategy;
import com.example.demo.strategy.UsTaxStrategy;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class TaxService {

    public BigDecimal calculateFinalPrice(Product product) {
        if (product == null || product.getCountry() == null) {
            throw new IllegalArgumentException("No tax strategy for this country : " + product.getCountry());
        }
        TaxStrategy taxStrategy = getTaxStrategy(product.getCountry());
        BigDecimal tax = taxStrategy.calculateTax(product);
        return product.getPrice().add(tax).setScale(2, RoundingMode.HALF_UP);
    }

    private TaxStrategy getTaxStrategy(Country country) {
        switch (country) {
            case USA:
                return new UsTaxStrategy();
            case CANADA:
                return new CanadaTaxStrategy();
            case FRANCE:
                return new FranceTaxStrategy();
            default:
                throw new IllegalArgumentException("No tax strategy for provided country : " + country);
        }
    }
}

