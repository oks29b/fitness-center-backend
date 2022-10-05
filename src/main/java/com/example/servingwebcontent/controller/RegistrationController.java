package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.model.entity.Role;
import com.example.servingwebcontent.model.repository.UserRepository;
import com.example.servingwebcontent.model.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
public class RegistrationController {
    private UserRepository userRepo;

    public RegistrationController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }


    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Model model) {
        User userFromDb = userRepo.findByUsername(user.getUsername());
        if (userFromDb != userRepo.findByUsername(user.getUsername())) {
            model.addAttribute("post", "User exists");
            return "registration";
        }

        user.setActive(true);
        user.setRole(Collections.singleton(Role.USER));
        userRepo.save(user);
        return "redirect:/login";
    }
}
