package com.example.servingwebcontent.model.repository;

import com.example.servingwebcontent.model.entity.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {
}
