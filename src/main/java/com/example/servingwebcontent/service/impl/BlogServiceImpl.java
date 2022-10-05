package com.example.servingwebcontent.service.impl;

import com.example.servingwebcontent.model.entity.Post;
import com.example.servingwebcontent.model.repository.PostRepository;
import com.example.servingwebcontent.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<Post> blogGetMain() {
        List<Post> posts = postRepository.findAll();
        return posts;
    }

    @Override
    public Post blogPostAdd(String titleWorkout, String workoutDay, String descriptionWorkout, int durationOfTraining) {
        Post post = new Post(titleWorkout, workoutDay, descriptionWorkout, durationOfTraining);
        return postRepository.save(post);
    }

    @Override
    public List<Post> blogPostDetails(long id) {
        Optional<Post> post = postRepository.findById(id);
        List<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        return res;
    }

    @Override
    public List<Post> blogEditInfo(long id) {
        Optional<Post> post = postRepository.findById(id);
        List<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        return res;
    }

    @Override
    public Post blogPostUpdate(long id, String titleWorkout, String workoutDay, String descriptionWorkout, int durationOfTraining) {
        Post post = postRepository.findById(id).orElseThrow();
        post.setTitleWorkout(titleWorkout);
        post.setWorkoutDay(workoutDay);
        post.setDescriptionWorkout(descriptionWorkout);
        post.setDurationOfTraining(durationOfTraining);
        return postRepository.save(post);
    }

    @Override
    public Post blogPostRemove(long id) {
        Post post = postRepository.findById(id).orElseThrow();
        postRepository.delete(post);
        return post;
    }
}
