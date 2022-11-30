package com.example.servingwebcontent.service;

import com.example.servingwebcontent.exceptionhandling.NotEnoughProductsInStockException;
import com.example.servingwebcontent.model.entity.Order;
import com.example.servingwebcontent.model.entity.Product;
import com.example.servingwebcontent.model.entity.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface ShoppingOrderService {

    List<Order> getOrdersByUserId(Long id);

    void addProductToOrder(Long productId, User user);

    void removeProduct(Product product);

    Map<Product, Integer> getProductsInOrder();

    void checkout() throws NotEnoughProductsInStockException;

    BigDecimal getTotal();

}
