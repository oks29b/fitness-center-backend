package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.model.entity.Post;
import com.example.servingwebcontent.model.repository.PostRepository;
import com.example.servingwebcontent.service.BlogService;
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
    private PostRepository postRepository;
    private BlogService blogService = new BlogService();

    @Autowired
    public BlogController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping("/blog")
    public String blogMain(Model model) {
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "blog-main";
    }

    @GetMapping("/blog/add")
    public String blogAdd(Model model) {
        return "blog-add";
    }

    @PostMapping("/blog/add")
    public String blogPostAddInfo(@RequestParam String title_workout, @RequestParam String workout_day, @RequestParam String description_workout,
                                  @RequestParam int duration_of_training, Model model) {
        blogService.blogPostAdd(postRepository, title_workout, workout_day, description_workout, duration_of_training, model);
        return "redirect:/blog";
    }

    @GetMapping("/blog/{id}")
    public String blogDetails(@PathVariable(value = "id") long id, Model model) {
        if (!postRepository.existsById(id)) {
            return "redirect:/blog";
        }
        blogService.blogDetails(postRepository, id, model);
        return "blog-details";
    }

    @GetMapping("/blog/{id}/edit")
    public String blogEdit(@PathVariable(value = "id") long id, Model model) {
        if (!postRepository.existsById(id)) {
            return "redirect:/blog";
        }
        Optional<Post> post = postRepository.findById(id);
        List<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post", res);
        return "blog-edit";
    }

    @PostMapping("/blog/{id}/edit")
    public String blogPostUpdateInfo(@PathVariable(value = "id") long id, @RequestParam String title_workout, @RequestParam String workout_day, @RequestParam String description_workout,
                                     @RequestParam int duration_of_training, Model model) {
        blogService.blogPostUpdate(postRepository, id, title_workout, workout_day, description_workout, duration_of_training, model);
        return "redirect:/blog";
    }

    @PostMapping("/blog/{id}/remove")
    public String blogPostRemoveFromList(@PathVariable(value = "id") long id, Model model) {
        blogService.blogPostRemove(postRepository, id, model);
        return "redirect:/blog";
    }
}
