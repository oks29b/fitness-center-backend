package com.example.servingwebcontent.service;

import com.example.servingwebcontent.model.entity.Post;
import com.example.servingwebcontent.model.repository.PostRepository;
import org.springframework.ui.Model;

public class BlogService implements BlogPostInterface{

    public BlogService() {
    }

    @Override
    public void blogPostRemove(PostRepository postRepository, long id, Model model) {
        Post post = postRepository.findById(id).orElseThrow();
        postRepository.delete(post);
    }
}
