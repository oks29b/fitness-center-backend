package com.example.servingwebcontent.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title_workout, workout_day, description_workout;
    private int duration_of_training;

    public Post(String title_workout, String workout_day, String description_workout, int duration_of_training) {
        this.title_workout = title_workout;
        this.workout_day = workout_day;
        this.description_workout = description_workout;
        this.duration_of_training = duration_of_training;
    }

    public Post() {
    }

    public Long getId() {
        return id;
    }

    public String getTitle_workout() {
        return title_workout;
    }

    public String getWorkout_day() {
        return workout_day;
    }

    public String getDescription_workout() {
        return description_workout;
    }

    public int getDuration_of_training() {
        return duration_of_training;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle_workout(String title_workout) {
        this.title_workout = title_workout;
    }

    public void setWorkout_day(String workout_day) {
        this.workout_day = workout_day;
    }

    public void setDescription_workout(String description_workout) {
        this.description_workout = description_workout;
    }

    public void setDuration_of_training(int duration_of_training) {
        this.duration_of_training = duration_of_training;
    }

}
