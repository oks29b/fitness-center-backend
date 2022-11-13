package com.example.servingwebcontent.service;

import com.example.servingwebcontent.model.entity.Post;
import com.example.servingwebcontent.model.entity.User;


import java.util.List;

public interface BlogService {
    public List<Post> blogGetMain(Long userId);

    List<Post> blogFilter(String filter, List<Post> posts);

    public List<Post> findAll();

    public Post blogPostAdd(String titleWorkout, String workoutDay,
                                      String descriptionWorkout, int durationOfTraining,String filename, User user);


    public List<Post> blogPostDetails(long id);

    public List<Post> blogEditInfo(long id);

    public Post blogPostUpdate(long id, String titleWorkout, String workoutDay,
                               String descriptionWorkout, int durationOfTraining, String filename);

    public Post blogPostRemove(long id);
}
