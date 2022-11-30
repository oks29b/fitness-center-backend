package com.example.servingwebcontent.service.impl;

import com.example.servingwebcontent.exceptionhandling.NotEnoughProductsInStockException;
import com.example.servingwebcontent.model.entity.*;
import com.example.servingwebcontent.model.repository.OrderDetailsRepository;
import com.example.servingwebcontent.model.repository.OrderRepository;
import com.example.servingwebcontent.model.repository.ProductRepository;
import com.example.servingwebcontent.model.repository.UserRepository;
import com.example.servingwebcontent.service.ShoppingOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

/**
 * ShoppingOrderServiceImpl is implemented ShoppingOrderService.
 *
 * @author Oksana Borisenko
 */

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
@RequiredArgsConstructor
public class ShoppingOrderServiceImpl implements ShoppingOrderService {

    private final ProductRepository productRepository;
    private final OrderDetailsRepository orderDetailsRepository;
    private final UserRepository userRepository;
    private Map<Product, Integer> products = new HashMap<>();

    /**
     * If product is in the map just increment quantity by 1.
     * If product is not in the map with, add it with quantity 1
     *
     * @param product
     */
    public void addProduct(Product product) {
        if (products.containsKey(product)) {
            products.replace(product, products.get(product) + 1);
        } else {
            products.put(product, 1);
        }
    }


    @Override
    public List<Order> getOrdersByUserId(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("There is no user with id = " + id));
        return user.getOrders();
    }

    /**
     * Add product to OrderDetails.
     *
     * @param productId
     * @param user
     */
    @Override
    public void addProductToOrder(Long productId, User user) {
        Product productDb = productRepository.findProductById(productId);
        addProduct(productDb);
        int quantity = products.getOrDefault(productDb, 0);
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setTotalQuantity(quantity);
        orderDetail.setTotalPrice(productDb.getPrice().multiply(BigDecimal.valueOf(quantity)));
        orderDetail.setProduct(productDb);
        orderDetail.setOrder(createNewOrder(user));
        orderDetailsRepository.save(orderDetail);
    }

    /**
     * Create new order.
     *
     * @param user
     */
    private Order createNewOrder(User user) {
        Order order = new Order();
        order.setOrderStatus(Status.ACTIVE);
        order.setOrderDateTime(LocalDateTime.now());
        order.setUser(user);
        return order;
    }


    /**
     * If product is in the map with quantity > 1, just decrement quantity by 1.
     * If product is in the map with quantity 1, remove it from map
     *
     * @param product
     */
    @Override
    public void removeProduct(Product product) {
        if (products.containsKey(product)) {
            if (products.get(product) > 1)
                products.replace(product, products.get(product) - 1);
            else if (products.get(product) == 1) {
                products.remove(product);
            }
        }
    }

    /**
     * @return unmodifiable copy of the map
     */
    @Override
    public Map<Product, Integer> getProductsInOrder() {
        return Collections.unmodifiableMap(products);
    }

    /**
     * Checkout will rollback if there is not enough of some product in stock
     *
     * @throws NotEnoughProductsInStockException
     */
    @Override
    public void checkout() throws NotEnoughProductsInStockException {
        Product product;
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            // Refresh quantity for every product before checking
            product = productRepository.findById(entry.getKey().getId()).orElse(null);
            if (product.getCurrentQuantity() < entry.getValue())
                throw new NotEnoughProductsInStockException(product);
            entry.getKey().setCurrentQuantity(product.getCurrentQuantity() - entry.getValue());
        }
        productRepository.saveAllAndFlush(products.keySet());
        products.clear();
    }

    @Override
    public BigDecimal getTotal() {
        return products.entrySet().stream()
                .map(entry -> entry.getKey().getPrice().multiply(BigDecimal.valueOf(entry.getValue())))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }
}
