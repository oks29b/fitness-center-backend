package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.exceptionhandling.NotEnoughProductsInStockException;
import com.example.servingwebcontent.model.entity.Order;
import com.example.servingwebcontent.model.entity.OrderDetail;
import com.example.servingwebcontent.model.entity.Product;
import com.example.servingwebcontent.model.entity.User;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

/**
 * @author Oksana Borisenko
 */
@Controller
@RequiredArgsConstructor
public class ShoppingOrderController {

    private final ShoppingOrderService shoppingOrderService;
    private final UserDetailsServiceImpl userDetailsService;

    private final ProductService productService;

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
        return "shoppingCart";
    }

    @GetMapping("/shoppingCart/addProduct/{productId}")
    public String addProductToCart(@AuthenticationPrincipal UserDetails userDetails, @PathVariable("productId") Long productId, Model model) {

        User user = userDetailsService.findByUsername(userDetails.getUsername());



        shoppingOrderService.addProductToOrder(productId, user);

        return "shoppingCart";
    }


    @GetMapping("/shoppingCart/checkout")
    public String checkout(Model model) {
        try {
            shoppingOrderService.checkout();
        } catch (NotEnoughProductsInStockException e) {
            model.addAttribute("outOfStockMessage", e.getMessage());
            return "shoppingCart";
        }

        return "shoppingCart";
    }
}
