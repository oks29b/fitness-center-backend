//package com.example.servingwebcontent.controller;
//
//import com.example.servingwebcontent.repo.Role;
//import com.example.servingwebcontent.repo.UserRepo;
//import com.example.servingwebcontent.repo.Users;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//
//import java.util.Collections;
//
//public class LoginController {
//    @Autowired
//    private UserRepo userRepo;
//
//    @GetMapping("/login")
//    public String registration() {
//        return "login";
//    }
//
//    @PostMapping("/login")
//    public String enterLogin(Users user, Model model) {
//        Users userFromDb = userRepo.findByUsername(user.getUsername());
//        if (userFromDb == userRepo.findByUsername(user.getUsername())) {
//            model.addAttribute("post", "User welcome");
//        }
//        return "redirect:/blog";
//    }
//}
