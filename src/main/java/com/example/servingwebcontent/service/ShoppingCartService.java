package com.example.servingwebcontent.service;

import com.example.servingwebcontent.exceptionhandling.NotEnoughProductsInStockException;
import com.example.servingwebcontent.model.entity.Product;

import java.math.BigDecimal;
import java.util.Map;

public interface ShoppingCartService {

    void addProductToOrder(Product product);

    void removeProduct(Product product);

    Map<Product, Integer> getProductsInCart();

    void checkout() throws NotEnoughProductsInStockException;

    BigDecimal getTotal();
}
