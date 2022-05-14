package com.example.servingwebcontent.controller;

//import com.example.servingwebcontent.repo.Post;
//import com.example.servingwebcontent.repo.Role;
//import com.example.servingwebcontent.repo.UserRepo;
//import com.example.servingwebcontent.repo.Users;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
//import java.util.Map;

@Controller
public class MainController {

    @GetMapping("/")
    public String greeting(Model model) {
        model.addAttribute("title", "Hello, sports world!");
        return "home";
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("title", "О нас");
        return "about";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

//    @Autowired
//    private UserRepo userRepo;
//
//    @GetMapping("/registration")
//    public String registration(){
//        return "registration";
//    }
//
//    @PostMapping("/registration")
//    public String addUser(Users user, Model model){
//        Users userFromDb = userRepo.findByUsername(user.getUsername());
//        if(userFromDb != userRepo.findByUsername(user.getUsername())){
//            model.addAttribute("post", "User axists");
//            return "registration";
//        }
//
//        user.setActive(true);
//        user.setRoles(Collections.singleton(Role.USER));
//        userRepo.save(user);
//        return "redirect:/login";
//    }
}
