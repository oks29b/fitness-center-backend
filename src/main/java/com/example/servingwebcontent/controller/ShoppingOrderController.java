package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.exceptionhandling.NotEnoughProductsInStockException;
import com.example.servingwebcontent.model.entity.Order;
import com.example.servingwebcontent.model.entity.OrderDetail;
import com.example.servingwebcontent.model.entity.Product;
import com.example.servingwebcontent.model.entity.User;
import com.example.servingwebcontent.model.repository.OrderRepository;
import com.example.servingwebcontent.security.UserDetailsServiceImpl;
import com.example.servingwebcontent.service.ProductService;
import com.example.servingwebcontent.service.ShoppingOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Oksana Borisenko
 */
@Controller
@RequiredArgsConstructor
public class ShoppingOrderController {

    private static final String REDIRECT_SHOPPING_CART = "redirect:/shoppingCart";

    private final ShoppingOrderService shoppingOrderService;
    private final UserDetailsServiceImpl userDetailsService;
    private final ProductService productService;

    private final OrderRepository orderRepository;

    @GetMapping("/shoppingCart")
    public String shoppingCart(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        User user = userDetailsService.findByUsername(userDetails.getUsername());
        List<Order> orders = shoppingOrderService.getOrdersByUserId(user.getId());

        if(orders == null){
            model.addAttribute("orders", "There is no any products");
            return "shoppingCart";
        }

        Map<Order, List<Product>> userOrderProductList = new HashMap<>();

        for (Order order : orders) {
            List<OrderDetail> orderDetails = order.getOrderDetails();
            List<Product> products = new ArrayList<>();
            for (OrderDetail od: orderDetails) {
                products.add(od.getProduct());
            }
            userOrderProductList.put(order, products);
        }
        model.addAttribute("orderProducts", userOrderProductList);
        model.addAttribute("total", shoppingOrderService.getTotal().toString());

        return "shoppingCart";
    }

    @GetMapping("/shoppingCart/addProduct/{productId}")
    public String addProductToCart(@AuthenticationPrincipal UserDetails userDetails, @PathVariable("productId") Long productId, Model model) {
        User user = userDetailsService.findByUsername(userDetails.getUsername());
        shoppingOrderService.addProductToOrder(productId, user);

        return REDIRECT_SHOPPING_CART;
    }

    @GetMapping("/shoppingCart/checkout")
    public String checkout(Model model) {
        try {
            shoppingOrderService.checkout();
            model.addAttribute("checkout", "Congratulations! Your order has been successfully placed");
        } catch (NotEnoughProductsInStockException e) {
            model.addAttribute("outOfStockMessage", e.getMessage());
            return "shoppingCart";
        }

        return "checkout";
    }

    @GetMapping("/shoppingCart/removeProduct/{productId}")
    public String removeProductFromCart(@PathVariable("productId") Long productId) {
        productService.findById(productId).ifPresent(shoppingOrderService::removeProduct);
        return REDIRECT_SHOPPING_CART;
    }
}
