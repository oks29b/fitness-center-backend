package com.example.servingwebcontent.model.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String titleWorkout, workoutDay, descriptionWorkout;
    int durationOfTraining;

    public Post(String titleWorkout, String workoutDay, String descriptionWorkout, int durationOfTraining) {
        this.titleWorkout = titleWorkout;
        this.workoutDay = workoutDay;
        this.descriptionWorkout = descriptionWorkout;
        this.durationOfTraining = durationOfTraining;
    }

}
