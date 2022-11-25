package com.example.servingwebcontent.service;

import com.example.servingwebcontent.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.Optional;

public interface ProductService {

    Optional<Product> findById(Long id);

    Page<Product> findAllProductsPageable(Pageable pageable);

    boolean addProduct(Product product);

    void updateProduct(Product product, String description, Integer quantity, BigDecimal price);

    void removeProduct(Long id);

}
