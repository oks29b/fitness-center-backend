package com.example.servingwebcontent.service;

import com.example.servingwebcontent.model.entity.Post;
import com.example.servingwebcontent.model.repository.PostRepository;
import org.springframework.ui.Model;

import java.util.List;

public interface BlogService {
    public List<Post> blogGetMain();

    public Post blogPostAdd(String titleWorkout, String workoutDay,
                                      String descriptionWorkout, int durationOfTraining);

    public List<Post> blogPostDetails(long id);

    public List<Post> blogEditInfo(long id);

    public Post blogPostUpdate(long id, String titleWorkout, String workoutDay,
                               String descriptionWorkout, int durationOfTraining);

    public Post blogPostRemove(long id);
}
