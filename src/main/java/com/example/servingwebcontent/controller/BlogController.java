package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.model.entity.Post;
import com.example.servingwebcontent.model.entity.User;
import com.example.servingwebcontent.model.repository.UserRepository;
import com.example.servingwebcontent.service.impl.BlogServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;


@Controller
public class BlogController {
    private static final String REDIRECT_BLOG = "redirect:/blog";

    @Value("${upload.path}")
    private String uploadPath;

    private final BlogServiceImpl blogService;
    private final UserRepository userRepository;

    public BlogController(BlogServiceImpl blogService, UserRepository userRepository) {
        this.blogService = blogService;
        this.userRepository = userRepository;
    }

    @GetMapping("/blog")
    public String blogMain(
            @AuthenticationPrincipal UserDetails userDetails,
            Model model
    ) {
        User user = userRepository.findByUsername(userDetails.getUsername());
        List<Post> posts = blogService.blogGetMain(user.getId());
        model.addAttribute("posts", posts);
        return "blog-main";
    }

    @GetMapping("/blog/add")
    public String blogAdd(Model model) {
        return "blog-add";
    }

    @GetMapping("/blogFilter")
    public String blogFilter(@RequestParam(required = false, defaultValue = "") String filter,
                             @AuthenticationPrincipal UserDetails userDetails,
                             Model model){
        User user = userRepository.findByUsername(userDetails.getUsername());
        List<Post> posts = blogService.blogGetMain(user.getId());
        if(posts != null) {
            model.addAttribute("posts", blogService.blogFilter(filter, posts));
            model.addAttribute("filter", filter);
        }
    return "blog-main";
    }

    @PostMapping("/blog/add")
    public String blogPostAddInfo(@RequestParam String titleWorkout,
                                  @RequestParam String workoutDay,
                                  @RequestParam String descriptionWorkout,
                                  @RequestParam int durationOfTraining,
                                  @AuthenticationPrincipal UserDetails userDetails,
                                  Model model,
                                  @RequestParam("file")MultipartFile file) throws IOException {
        User user = userRepository.findByUsername(userDetails.getUsername());

        blogService.blogPostAdd(titleWorkout, workoutDay, descriptionWorkout, durationOfTraining, getFileName(file), user);

        return REDIRECT_BLOG;
    }

    private String getFileName(MultipartFile file) throws IOException {
        String resultFileName = "";

        if(file != null && !file.getOriginalFilename().isEmpty()){
            File uploadDir = new File(uploadPath);

            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            resultFileName = uuidFile + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + resultFileName));
        }else {resultFileName = null;}
        return resultFileName;
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
    public String blogPostUpdateInfo(@PathVariable(value = "id") long id,
                                     @RequestParam String titleWorkout,
                                     @RequestParam String workoutDay,
                                     @RequestParam String descriptionWorkout,
                                     @RequestParam int durationOfTraining,
                                     @RequestParam("file") MultipartFile file,
                                     Model model) throws IOException {

            blogService.blogPostUpdate(id, titleWorkout, workoutDay, descriptionWorkout, durationOfTraining, getFileName(file));

        return REDIRECT_BLOG;
    }

    @PostMapping("/blog/{id}/remove")
    public String blogPostRemoveFromList(@PathVariable(value = "id") long id, Model model) {
        blogService.blogPostRemove(id);
        return REDIRECT_BLOG;
    }
}
