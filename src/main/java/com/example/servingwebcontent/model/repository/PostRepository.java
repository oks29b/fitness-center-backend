package com.example.servingwebcontent.model.repository;

import com.example.servingwebcontent.model.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query(value = "select * from post where user_id = ?1", nativeQuery = true)
    List<Post> findAllByUserId(Long userId);
}
