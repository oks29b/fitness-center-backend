package com.example.servingwebcontent.service.impl;

import com.example.servingwebcontent.exceptionhandling.NoSuchPostException;
import com.example.servingwebcontent.model.entity.Post;
import com.example.servingwebcontent.model.entity.User;
import com.example.servingwebcontent.model.repository.PostRepository;
import com.example.servingwebcontent.service.BlogService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BlogServiceImpl implements BlogService {
    private final PostRepository postRepository;

    public BlogServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostRepository getPostRepository() {
        return postRepository;
    }

    @Override
    public List<Post> blogGetMain(Long userId) {
        List<Post> posts = postRepository.findAllByUserId(userId);
        return posts;
    }

    @Override
    public List<Post> blogFilter(String filter, List<Post> posts){
        List<Post> filterPost = new ArrayList<>();
        if(filter != null && !filter.isEmpty()) {
            for (Post i : posts) {
                if (i.getTitleWorkout().equals(filter)) {
                    filterPost.add(i);
                }
            }
            return filterPost;
        }else {
            return posts;
        }
    }

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public Post blogPostAdd(String titleWorkout, String workoutDay,
                            String descriptionWorkout, int durationOfTraining,String filename, User user) {
        Post post = new Post(titleWorkout, workoutDay, descriptionWorkout, durationOfTraining, filename, user);
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
    public Post blogPostUpdate(long id, String titleWorkout, String workoutDay, String descriptionWorkout, int durationOfTraining, String fileName) {
        Post post = postRepository.findById(id).orElseThrow(() -> new NoSuchPostException("There is no post with id = " + id));
        post.setTitleWorkout(titleWorkout);
        post.setWorkoutDay(workoutDay);
        post.setDescriptionWorkout(descriptionWorkout);
        post.setDurationOfTraining(durationOfTraining);
        post.setFileName(fileName);
        return postRepository.save(post);
    }

    @Override
    public Post blogPostRemove(long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new NoSuchPostException("There is no post with id = " + id));
        postRepository.delete(post);
        return post;
    }
}
