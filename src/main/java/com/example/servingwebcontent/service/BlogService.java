package com.example.servingwebcontent.service;

import com.example.servingwebcontent.model.entity.Post;
import com.example.servingwebcontent.model.repository.PostRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BlogService implements BlogPostInterface{

    public BlogService() {
    }

    @Override
    public void blogPostAdd(PostRepository postRepository, String title_workout, String workout_day, String description_workout, int duration_of_training, Model model){
        Post post = new Post(title_workout, workout_day, description_workout, duration_of_training);
        postRepository.save(post);
    }

    @Override
    public void blogDetails(PostRepository postRepository, long id, Model model) {
        Optional<Post> post = postRepository.findById(id);
        List<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post",res);
    }

    @Override
    public void blogPostUpdate(PostRepository postRepository, long id, String title_workout, String workout_day, String description_workout, int duration_of_training, Model model) {
        Post post = postRepository.findById(id).orElseThrow();
        post.setTitle_workout(title_workout);
        post.setWorkout_day(workout_day);
        post.setDescription_workout(description_workout);
        post.setDuration_of_training(duration_of_training);
        postRepository.save(post);
    }

    @Override
    public void blogPostRemove(PostRepository postRepository, long id, Model model) {
        Post post = postRepository.findById(id).orElseThrow();
        postRepository.delete(post);
    }
}
