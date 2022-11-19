package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.model.entity.Role;
import com.example.servingwebcontent.model.entity.Status;
import com.example.servingwebcontent.model.entity.User;
import com.example.servingwebcontent.security.UserDetailsServiceImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserDetailsServiceImpl userDetailsService;

    public UserController(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
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


//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping
    public String userSave(
            @RequestParam String username,
            @RequestParam Map<String , String> form,
            @RequestParam String status,
            @RequestParam("userId") User user
    ){
        userDetailsService.saveUser(user, username, form, status);

        return "redirect:/user";
    }

    @GetMapping("profile")
    public String getProfile(Model model, @AuthenticationPrincipal UserDetails userDetails){
        User user = userDetailsService.findByUsername(userDetails.getUsername());
        model.addAttribute("username", user.getUsername());
        model.addAttribute("email", user.getEmail());
        return "profile";
    }


    @PostMapping("profile")
    public String updateProfile(@AuthenticationPrincipal UserDetails userDetails,
                                @RequestParam String password,
                                @RequestParam String email){
        User user = userDetailsService.findByUsername(userDetails.getUsername());
        userDetailsService.updateProfile(user, password, email);

        return "redirect:/user/profile";
    }
}
