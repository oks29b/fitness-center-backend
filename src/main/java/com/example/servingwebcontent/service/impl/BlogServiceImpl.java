package com.example.servingwebcontent.service.impl;

import com.example.servingwebcontent.model.entity.Post;
import com.example.servingwebcontent.model.repository.PostRepository;
import com.example.servingwebcontent.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BlogServiceImpl implements BlogService {
    private PostRepository postRepository;

    public BlogServiceImpl() {
    }

    @Autowired
    public BlogServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostRepository getPostRepository() {
        return postRepository;
    }

    @Override
    public void blogGetMain(Model model) {
        List<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
    }

    @Override
    public void blogPostAdd(String titleWorkout, String workoutDay, String descriptionWorkout, int durationOfTraining, Model model) {
        Post post = new Post(titleWorkout, workoutDay, descriptionWorkout, durationOfTraining);
        postRepository.save(post);
    }

    @Override
    public void blogPostDetails(long id, Model model) {
        Optional<Post> post = postRepository.findById(id);
        List<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post", res);
    }

    @Override
    public void blogEditInfo(long id, Model model) {
        Optional<Post> post = postRepository.findById(id);
        List<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post", res);
    }

    @Override
    public void blogPostUpdate(long id, String titleWorkout, String workoutDay, String descriptionWorkout, int durationOfTraining, Model model) {
        Post post = postRepository.findById(id).orElseThrow();
        post.setTitleWorkout(titleWorkout);
        post.setWorkoutDay(workoutDay);
        post.setDescriptionWorkout(descriptionWorkout);
        post.setDurationOfTraining(durationOfTraining);
        postRepository.save(post);
    }

    @Override
    public void blogPostRemove(long id, Model model) {
        Post post = postRepository.findById(id).orElseThrow();
        postRepository.delete(post);
    }
}
