package com.example.servingwebcontent.service;

import com.example.servingwebcontent.model.entity.Post;
import com.example.servingwebcontent.model.entity.User;


import java.util.List;

public interface BlogService {
    List<Post> blogGetMain(Long userId);

    List<Post> blogFilter(String filter, List<Post> posts);

    List<Post> findAll();

    Post blogPostAdd(String titleWorkout, String workoutDay,
                                      String descriptionWorkout, int durationOfTraining,String filename, User user);


    List<Post> blogPostDetails(long id);

    List<Post> blogEditInfo(long id);

    Post blogPostUpdate(long id, String titleWorkout, String workoutDay,
                               String descriptionWorkout, int durationOfTraining, String filename);

    void blogPostRemove(long id);
}
