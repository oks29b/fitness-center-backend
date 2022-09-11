package com.example.servingwebcontent.service;

import com.example.servingwebcontent.model.repository.PostRepository;
import org.springframework.ui.Model;

public interface BlogPostInterface {
    public void blogPostRemove(PostRepository postRepository, long id, Model model);


//    public String blogAdd(Model model);
//    public String blogDetails(long id, Model model);
//    public String blogEdit(long id, Model model);
}
