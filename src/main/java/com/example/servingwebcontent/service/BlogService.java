package com.example.servingwebcontent.service;

import org.springframework.ui.Model;

public interface BlogService {
    public void blogGetMain(Model model);
    public void blogPostAdd(String titleWorkout, String workoutDay,
                            String descriptionWorkout, int durationOfTraining, Model model);

    public void blogPostDetails(long id, Model model);

    public void blogPostUpdate(long id, String titleWorkout, String workoutDay,
                               String descriptionWorkout, int durationOfTraining, Model model);

    public void blogPostRemove(long id, Model model);
}
