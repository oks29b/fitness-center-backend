package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.model.entity.Product;
import com.example.servingwebcontent.service.ProductService;
import com.example.servingwebcontent.util.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

/**
 * @author Oksana Borisenko
 */
@Controller
public class ShopController {

    private static final int INITIAL_PAGE = 0;

    private final ProductService productService;

    @Autowired
    public ShopController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/shop")
    public String shop(@RequestParam("page") Optional<Integer> page, Model model) {

        /**
         * Evaluate page. If requested parameter is null or less than 0 (to
         * prevent exception), return initial size. Otherwise, return value of
         * param. decreased by 1.
         */

        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

        Page<Product> products = productService.findAllProductsPageable(PageRequest.of(evalPage, 5));
        Pager pager = new Pager(products);

        model.addAttribute("products", products);
        model.addAttribute("pager", pager);
        return "shop";
    }

}
