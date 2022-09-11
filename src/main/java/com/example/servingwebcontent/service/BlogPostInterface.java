package com.example.servingwebcontent.service;

import com.example.servingwebcontent.model.entity.Post;
import com.example.servingwebcontent.model.repository.PostRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

public interface BlogPostInterface {
    public void blogPostAdd(PostRepository postRepository, String title_workout, String workout_day, String description_workout, int duration_of_training, Model model);

    public void blogDetails(PostRepository postRepository, long id, Model model);

    public void blogPostUpdate(PostRepository postRepository, long id, String title_workout, String workout_day, String description_workout, int duration_of_training, Model model);

    public void blogPostRemove(PostRepository postRepository, long id, Model model);
}
