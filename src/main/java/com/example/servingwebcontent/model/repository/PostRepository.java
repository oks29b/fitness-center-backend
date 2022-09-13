package com.example.servingwebcontent.model.repository;

import com.example.servingwebcontent.model.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
