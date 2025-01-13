package com.example.demo.service;

import com.example.demo.model.Country;
import com.example.demo.model.Product;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private final List<Product> products = new ArrayList<>();

    public ProductService() {
        products.add(new Product(1L, "Product 1", new BigDecimal("100.00"), Country.FRANCE));
        products.add(new Product(2L, "Product 2", new BigDecimal("200.00"), Country.USA));
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public Product getProductById(Long id) {
        return products.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public BigDecimal calculateFinalPrice(Product product) {
        return null;
    }
}
