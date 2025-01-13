package com.example.demo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductControllerRestTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;



    @Test
    void testGetFinalPriceProductIdNonValid() {
        String url = "http://localhost:" + port + "/products/price_after_tax/9999";

        ResponseEntity<String> response = testRestTemplate.getForEntity(url, String.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void testGetFinalPriceForExistingId() {
        String url = "http://localhost:" + port + "/products/price_after_tax/1";

        ResponseEntity<BigDecimal> response = testRestTemplate.getForEntity(url, BigDecimal.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(new BigDecimal("120.00"), response.getBody());
    }
}
