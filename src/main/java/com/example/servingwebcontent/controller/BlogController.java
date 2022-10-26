package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.exceptionHandling.NoSuchPostException;
import com.example.servingwebcontent.model.entity.Post;
import com.example.servingwebcontent.model.entity.User;
import com.example.servingwebcontent.model.repository.UserRepository;
import com.example.servingwebcontent.service.impl.BlogServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class BlogController {
    private static final String REDIRECT_BLOG = "redirect:/blog";

    private final BlogServiceImpl blogService;
    private final UserRepository userRepository;

    public BlogController(BlogServiceImpl blogService, UserRepository userRepository) {
        this.blogService = blogService;
        this.userRepository = userRepository;
    }

    @GetMapping("/blog")
    public String blogMain(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        User user = userRepository.findByUsername(userDetails.getUsername());
        model.addAttribute("posts", blogService.blogGetMain(user.getId()));
        return "blog-main";
    }

    @GetMapping("/blog/add")
    public String blogAdd(Model model) {
        return "blog-add";
    }

    @PostMapping("/blog/add")
    public String blogPostAddInfo(@RequestParam String titleWorkout, @RequestParam String workoutDay, @RequestParam String descriptionWorkout,
                                  @RequestParam int durationOfTraining, @AuthenticationPrincipal UserDetails userDetails, Model model) {
        User user = userRepository.findByUsername(userDetails.getUsername());
        blogService.blogPostAdd(titleWorkout, workoutDay, descriptionWorkout, durationOfTraining, user);
        return REDIRECT_BLOG;
    }

    @GetMapping("/blog/{id}")
    public String blogDetails(@PathVariable(value = "id") long id, Model model) {
        if (!blogService.getPostRepository().existsById(id)) {
            return REDIRECT_BLOG;
        }
        model.addAttribute("post", blogService.blogPostDetails(id));
        return "blog-details";
    }

    @GetMapping("/blog/{id}/edit")
    public String blogEdit(@PathVariable(value = "id") long id, Model model) {
        if (!blogService.getPostRepository().existsById(id)) {
            return REDIRECT_BLOG;
        }
        model.addAttribute("post", blogService.blogEditInfo(id));
        return "blog-edit";
    }

    @PostMapping("/blog/{id}/edit")
    public String blogPostUpdateInfo(@PathVariable(value = "id") long id, @RequestParam String titleWorkout, @RequestParam String workoutDay,
                                     @RequestParam String descriptionWorkout, @RequestParam int durationOfTraining, Model model) {
        blogService.blogPostUpdate(id, titleWorkout, workoutDay, descriptionWorkout, durationOfTraining);
        return REDIRECT_BLOG;
    }

    @PostMapping("/blog/{id}/remove")
    public String blogPostRemoveFromList(@PathVariable(value = "id") long id, Model model) {
        blogService.blogPostRemove(id);
        return REDIRECT_BLOG;
    }
}
