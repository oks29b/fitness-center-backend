package com.example.servingwebcontent.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titleWorkout, workoutDay, descriptionWorkout;
    private int durationOfTraining;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public Post(String titleWorkout, String workoutDay, String descriptionWorkout, int durationOfTraining, User user) {
        this.titleWorkout = titleWorkout;
        this.workoutDay = workoutDay;
        this.descriptionWorkout = descriptionWorkout;
        this.durationOfTraining = durationOfTraining;
        this.user = user;
    }


}
