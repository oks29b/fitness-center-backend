package com.example.servingwebcontent.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author Oksana Borisenko
 */
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

    private String fileName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public Post(String titleWorkout, String workoutDay, String descriptionWorkout, int durationOfTraining, String fileName, User user) {
        this.titleWorkout = titleWorkout;
        this.workoutDay = workoutDay;
        this.descriptionWorkout = descriptionWorkout;
        this.durationOfTraining = durationOfTraining;
        this.fileName = fileName;
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return durationOfTraining == post.durationOfTraining && Objects.equals(id, post.id) && Objects.equals(titleWorkout, post.titleWorkout) && Objects.equals(workoutDay, post.workoutDay) && Objects.equals(descriptionWorkout, post.descriptionWorkout) && Objects.equals(fileName, post.fileName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titleWorkout, workoutDay, descriptionWorkout, durationOfTraining, fileName);
    }
}
