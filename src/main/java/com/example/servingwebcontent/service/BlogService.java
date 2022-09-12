package com.example.servingwebcontent.service;

import com.example.servingwebcontent.model.repository.PostRepository;
import org.springframework.ui.Model;

public interface BlogService {
    public void blogPostAdd(PostRepository postRepository, String titleWorkout, String workoutDay,
                            String descriptionWorkout, int durationOfTraining, Model model);

    public void blogDetails(PostRepository postRepository, long id, Model model);

    public void blogPostUpdate(PostRepository postRepository, long id, String titleWorkout, String workoutDay,
                               String descriptionWorkout, int durationOfTraining, Model model);

    public void blogPostRemove(PostRepository postRepository, long id, Model model);
}
