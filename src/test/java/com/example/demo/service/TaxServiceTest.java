package com.example.demo.service;

import com.example.demo.model.Country;
import com.example.demo.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

class TaxServiceTest {

    private TaxService taxService;

    @BeforeEach
    void setUp() {
        taxService = new TaxService();
    }

    @Test
    void testCalculateFinalPriceWithFranceTax() {
        Product product = new Product(1L, "Test Product", BigDecimal.valueOf(100), Country.FRANCE);
        BigDecimal expectedFinalPrice = BigDecimal.valueOf(120.00);

        BigDecimal actualFinalPrice = taxService.calculateFinalPrice(product);

        assertEquals(expectedFinalPrice.setScale(2), actualFinalPrice.setScale(2));
    }

    @Test
    void testCalculateFinalPriceWithUsTax() {
        Product product = new Product(2L, "US Product", BigDecimal.valueOf(100), Country.USA);
        BigDecimal expectedFinalPrice = BigDecimal.valueOf(110.00);

        BigDecimal actualFinalPrice = taxService.calculateFinalPrice(product);

        assertEquals(expectedFinalPrice.setScale(2), actualFinalPrice.setScale(2));
    }

    @Test
    void testCalculateFinalPriceWithInvalidCountry() {
        Product product = new Product(3L, "Invalid Product", BigDecimal.valueOf(100), null);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            taxService.calculateFinalPrice(product);
        });

    }
    @Test
    void testCalculateFinalPriceFree() {
        Product product = new Product(5L, "Free Product", BigDecimal.ZERO, Country.USA);
        BigDecimal expectedFinalPrice = BigDecimal.ZERO;

        BigDecimal actualFinalPrice = taxService.calculateFinalPrice(product);

        assertEquals(0, expectedFinalPrice.compareTo(actualFinalPrice));
    }

    @Test
    void testCalculateFinalPriceWithCheapPrice() {
        Product product = new Product(7L, "Cheap Product", new BigDecimal("0.0001"), Country.FRANCE);
        BigDecimal expectedFinalPrice = new BigDecimal("0.0001").multiply(new BigDecimal("1.20"))
                .setScale(2, RoundingMode.HALF_UP);
        BigDecimal actualFinalPrice = taxService.calculateFinalPrice(product);
        assertEquals(0, expectedFinalPrice.compareTo(actualFinalPrice));
    }



    @Test
    void testCalculateFinalPriceWithVeryBigPrice() {
        Product product = new Product(6L, "Bitcoin in few years", new BigDecimal("1000000000.99"), Country.CANADA);
        BigDecimal expectedFinalPrice = new BigDecimal("1150000001.14");

        BigDecimal actualFinalPrice = taxService.calculateFinalPrice(product);

        assertEquals(0, expectedFinalPrice.compareTo(actualFinalPrice));
    }


}
