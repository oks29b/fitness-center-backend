package com.example.servingwebcontent.service;

import com.example.servingwebcontent.exceptionhandling.NoSuchPostException;
import com.example.servingwebcontent.model.entity.Post;
import com.example.servingwebcontent.model.entity.User;
import com.example.servingwebcontent.model.repository.PostRepository;
import com.example.servingwebcontent.model.repository.UserRepository;
import com.example.servingwebcontent.service.impl.BlogServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class BlogServiceImplMockitoTest {

    @Mock
    private PostRepository postRepository;

    @Mock
    private BlogService blogService;

    @InjectMocks
    private BlogServiceImpl blogServiceImpl;

    @Mock
    private User user;

    @Mock
    private Post post;

    private List<Post> posts = new ArrayList<>();

    @Test
    public void blogGetMainMockitoTest(){

        Post post1 = new Post("first", "2022-11-01", "my-text", 0, null, user);
        Post post2 = new Post("sec", "2022-11-01", "my-more", 0, null, user);
        Post post3 = new Post("third", "2022-11-01", "my-text", 0, null, user);
        Post post4 = new Post("fourth", "2022-11-01", "another", 0, null, user);
        post1.setId(1L);
        post2.setId(2L);
        post3.setId(3L);
        post4.setId(4L);

        posts.add(post1);
        posts.add(post2);
        posts.add(post3);
        posts.add(post4);

        when(postRepository.findAllByUserId(user.getId())).thenReturn(posts);

        assertEquals(posts, blogServiceImpl.blogGetMain(user.getId()));
    }

    @Test
    public void blogFilterMockitoTest() {
        Post post1 = new Post("first", "2022-11-01", "my-text", 0, null, user);
        Post post2 = new Post("sec", "2022-11-01", "my-more", 0, null, user);
        Post post3 = new Post("third", "2022-11-01", "my-text", 0, null, user);
        Post post4 = new Post("fourth", "2022-11-01", "another", 0, null, user);
        post1.setId(1L);
        post2.setId(2L);
        post3.setId(3L);
        post4.setId(4L);

        posts.add(post1);
        posts.add(post2);
        posts.add(post3);
        posts.add(post4);

        List<Post> res = new ArrayList<>();
        res.add(post2);

        assertEquals(res, blogServiceImpl.blogFilter("sec", posts));
    }

    @Test
    public void findAllMockitoTest(){
        when(postRepository.findAll()).thenReturn(posts);

        assertEquals(posts, blogServiceImpl.findAll());
    }

    @Test
    public void blogEditInfoMockitoTest(){
        when(blogService.blogEditInfo(post.getId())).thenReturn(posts);
        assertEquals(posts, blogService.blogEditInfo(post.getId()));

        verify(blogService, times(1)).blogEditInfo(post.getId());
    }

    @Test
    public void blogPostUpdateMockitoTest(){
        when(blogService.blogPostUpdate(post.getId(), "first", "2022-11-01", "fix", 0, null)).thenReturn(post);
        assertEquals(post, blogService.blogPostUpdate(post.getId(), "first", "2022-11-01", "fix", 0, null));

        verify(blogService, times(1)).blogPostUpdate(post.getId(), "first", "2022-11-01", "fix", 0, null);
    }

    @Test
    public void blogPostRemoveMockitoTest(){
        blogService.blogPostRemove(post.getId());

        verify(blogService, times(1)).blogPostRemove(post.getId());

    }
    
}
