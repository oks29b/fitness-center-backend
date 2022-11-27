package com.example.servingwebcontent.model.repository;

import com.example.servingwebcontent.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findProductById(Long id);
    Product findProductByName(String name);
    Optional<Product> findById(Long id);
}
