package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.model.entity.Role;
import com.example.servingwebcontent.model.entity.Status;
import com.example.servingwebcontent.model.entity.User;
import com.example.servingwebcontent.security.UserDetailsServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author Oksana Borisenko
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserDetailsServiceImpl userDetailsService;

    public AdminController(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @GetMapping
    public String userList(Model model){
        model.addAttribute("users", userDetailsService.findAll());
        return "userList";
    }


    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user, Model model){
        model.addAttribute("user", user);
        model.addAttribute("status", Status.values());
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }

    @PostMapping
    public String userSave(
            @RequestParam String username,
            @RequestParam Map<String , String> form,
            @RequestParam String status,
            @RequestParam("userId") User user
    ){
        userDetailsService.saveUser(user, username, form, status);

        return "redirect:/admin";
    }
}
