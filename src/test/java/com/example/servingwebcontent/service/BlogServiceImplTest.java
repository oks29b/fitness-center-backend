package com.example.servingwebcontent.service;

import com.example.servingwebcontent.model.entity.Post;
import com.example.servingwebcontent.model.entity.User;
import com.example.servingwebcontent.model.repository.PostRepository;
import com.example.servingwebcontent.model.repository.UserRepository;
import com.example.servingwebcontent.service.impl.BlogServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = {"/create-user-before.sql", "/post-list-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/post-list-after.sql","/create-user-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class BlogServiceImplTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BlogServiceImpl blogService;

    @Test
    public void blogGetMainTest(){
        Long userId = 1L;
        Post post1 = new Post("first", "2022-11-01", "my-text", 0, null, userRepository.findById(userId).orElseThrow());
        Post post2 = new Post("sec", "2022-11-01", "my-more", 0, null, userRepository.findById(userId).orElseThrow());
        Post post3 = new Post("third", "2022-11-01", "my-text", 0, null, userRepository.findById(userId).orElseThrow());
        Post post4 = new Post("fourth", "2022-11-01", "another", 0, null, userRepository.findById(userId).orElseThrow());
        post1.setId(1L);
        post2.setId(2L);
        post3.setId(3L);
        post4.setId(4L);

        List<Post> expectedResult = new ArrayList<>();
        expectedResult.add(post1);
        expectedResult.add(post2);
        expectedResult.add(post3);
        expectedResult.add(post4);

        List<Post> actualResult = blogService.blogGetMain(userRepository.findById(userId).orElseThrow().getId());

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void blogFilterTest(){
        Long userId = 1L;
        Post post1 = new Post("first", "2022-11-01", "my-text", 0, null, userRepository.findById(userId).orElseThrow());
        Post post2 = new Post("sec", "2022-11-01", "my-more", 0, null, userRepository.findById(userId).orElseThrow());
        Post post3 = new Post("third", "2022-11-01", "my-text", 0, null, userRepository.findById(userId).orElseThrow());
        Post post4 = new Post("fourth", "2022-11-01", "another", 0, null, userRepository.findById(userId).orElseThrow());
        post1.setId(1L);
        post2.setId(2L);
        post3.setId(3L);
        post4.setId(4L);

        List<Post> posts = new ArrayList<>();
        posts.add(post1);
        posts.add(post2);
        posts.add(post3);
        posts.add(post4);

        List<Post> expectedResult = new ArrayList<>();
        expectedResult.add(post2);

        List<Post> actualResult = blogService.blogFilter("sec", posts);

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void findAllTest(){
        Long userId = 1L;
        Post post1 = new Post("first", "2022-11-01", "my-text", 0, null, userRepository.findById(userId).orElseThrow());
        Post post2 = new Post("sec", "2022-11-01", "my-more", 0, null, userRepository.findById(userId).orElseThrow());
        Post post3 = new Post("third", "2022-11-01", "my-text", 0, null, userRepository.findById(userId).orElseThrow());
        Post post4 = new Post("fourth", "2022-11-01", "another", 0, null, userRepository.findById(userId).orElseThrow());
        post1.setId(1L);
        post2.setId(2L);
        post3.setId(3L);
        post4.setId(4L);

        List<Post> expectedResult = new ArrayList<>();
        expectedResult.add(post1);
        expectedResult.add(post2);
        expectedResult.add(post3);
        expectedResult.add(post4);

        List<Post> actualResult = blogService.findAll();

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void blogEditInfoTest(){
        Long postId = 1L;
        Long userId = 1L;
        Post post1 = new Post("first", "2022-11-01", "my-text", 0, null, userRepository.findById(userId).orElseThrow());
        post1.setId(1L);

        List<Post> expectedResult = new ArrayList<>();
        expectedResult.add(post1);

        List<Post> actualResult = blogService.blogEditInfo(postId);

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void blogPostUpdateTest(){
        Long postId = 1L;
        String fix = "workout";
        Post expectedResult = postRepository.findById(postId).orElseThrow();
        expectedResult.setDescriptionWorkout(fix);
        postRepository.save(expectedResult);

        Post actualResult = blogService.blogPostUpdate(postId, "first", "2022-11-01", fix, 0, null);

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void blogPostRemoveTest(){
        Long postId = 1L;

        BlogService mockitoBlogService = Mockito.mock(BlogService.class);
        mockitoBlogService.blogPostRemove(postId);

        verify(mockitoBlogService, times(1)).blogPostRemove(postId);


    }
}
