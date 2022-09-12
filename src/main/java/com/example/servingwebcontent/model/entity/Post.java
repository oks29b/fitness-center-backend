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

    private String titleWorkout, workoutDay, descriptionWorkout;
    private int durationOfTraining;

    public Post(String titleWorkout, String workoutDay, String descriptionWorkout, int durationOfTraining) {
        this.titleWorkout = titleWorkout;
        this.workoutDay = workoutDay;
        this.descriptionWorkout = descriptionWorkout;
        this.durationOfTraining = durationOfTraining;
    }

    public Post() {
    }

    public Long getId() {
        return id;
    }

    public String getTitleWorkout() {
        return titleWorkout;
    }

    public String getWorkoutDay() {
        return workoutDay;
    }

    public String getDescriptionWorkout() {
        return descriptionWorkout;
    }

    public int getDurationOfTraining() {
        return durationOfTraining;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitleWorkout(String titleWorkout) {
        this.titleWorkout = titleWorkout;
    }

    public void setWorkoutDay(String workoutDay) {
        this.workoutDay = workoutDay;
    }

    public void setDescriptionWorkout(String descriptionWorkout) {
        this.descriptionWorkout = descriptionWorkout;
    }

    public void setDurationOfTraining(int durationOfTraining) {
        this.durationOfTraining = durationOfTraining;
    }

}
