package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.model.entity.Post;
import com.example.servingwebcontent.service.impl.BlogServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class BlogController {
    private static final String REDIRECT_BLOG = "redirect:/blog";

    private BlogServiceImpl blogService = new BlogServiceImpl();

    public BlogController(){
    }

    @Autowired
    public BlogController(BlogServiceImpl blogService) {
        this.blogService = blogService;
    }

    @GetMapping("/blog")
    public String blogMain(Model model) {
        blogService.blogGetMain(model);
        return "blog-main";
    }

    @GetMapping("/blog/add")
    public String blogAdd(Model model) {
        return "blog-add";
    }

    @PostMapping("/blog/add")
    public String blogPostAddInfo(@RequestParam String titleWorkout, @RequestParam String workoutDay, @RequestParam String descriptionWorkout,
                                  @RequestParam int durationOfTraining, Model model) {
        blogService.blogPostAdd(titleWorkout, workoutDay, descriptionWorkout, durationOfTraining, model);
        return REDIRECT_BLOG;
    }

    @GetMapping("/blog/{id}")
    public String blogDetails(@PathVariable(value = "id") long id, Model model) {
        if (!blogService.getPostRepository().existsById(id)) {
            return REDIRECT_BLOG;
        }
        blogService.blogPostDetails(id, model);
        return "blog-details";
    }

    @GetMapping("/blog/{id}/edit")
    public String blogEdit(@PathVariable(value = "id") long id, Model model) {
        if (!blogService.getPostRepository().existsById(id)) {
            return REDIRECT_BLOG;
        }
        blogService.blogEditInfo(id, model);
        return "blog-edit";
    }

    @PostMapping("/blog/{id}/edit")
    public String blogPostUpdateInfo(@PathVariable(value = "id") long id, @RequestParam String titleWorkout, @RequestParam String workoutDay,
                                     @RequestParam String descriptionWorkout, @RequestParam int durationOfTraining, Model model) {
        blogService.blogPostUpdate(id, titleWorkout, workoutDay, descriptionWorkout, durationOfTraining, model);
        return REDIRECT_BLOG;
    }

    @PostMapping("/blog/{id}/remove")
    public String blogPostRemoveFromList(@PathVariable(value = "id") long id, Model model) {
        blogService.blogPostRemove(id, model);
        return REDIRECT_BLOG;
    }
}
