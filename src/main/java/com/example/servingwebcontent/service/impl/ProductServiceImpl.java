package com.example.servingwebcontent.service.impl;

import com.example.servingwebcontent.model.entity.Product;
import com.example.servingwebcontent.model.repository.ProductRepository;
import com.example.servingwebcontent.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Page<Product> findAllProductsPageable(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public boolean addProduct(Product product) {
        Product productDb = productRepository.findProductByName(product.getName());

        if(productDb != null){
            return false;
        }

        productRepository.save(product);

        return true;
    }

    @Override
    public void updateProduct(Product product, String description, Integer quantity, BigDecimal price) {
        product.setDescription(description);
        product.setQuantity(quantity);
        product.setPrice(price);
        productRepository.save(product);
    }

    @Override
    public void removeProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new NoSuchElementException("There is no product with id = " + id));
        productRepository.delete(product);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }
}
