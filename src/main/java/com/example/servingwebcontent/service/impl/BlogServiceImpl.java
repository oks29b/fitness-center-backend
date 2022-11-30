package com.example.servingwebcontent.service.impl;

import com.example.servingwebcontent.exceptionhandling.NoSuchPostException;
import com.example.servingwebcontent.model.entity.Post;
import com.example.servingwebcontent.model.entity.User;
import com.example.servingwebcontent.model.repository.PostRepository;
import com.example.servingwebcontent.service.BlogService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * BlogServiceImpl is implemented BlogService.
 *
 * @author Oksana Borisenko
 */

@Service
public class BlogServiceImpl implements BlogService {
    private final PostRepository postRepository;

    public BlogServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostRepository getPostRepository() {
        return postRepository;
    }

    /**
     * @return all posts for user
     *
     * @param userId
     */
    @Override
    public List<Post> blogGetMain(Long userId) {
        return postRepository.findAllByUserId(userId);
    }

    /**
     * @return all posts which name equals filter
     *
     * @param filter
     * @param posts
     */
    @Override
    public List<Post> blogFilter(String filter, List<Post> posts){
        List<Post> filterPost = new ArrayList<>();
        if(filter != null && !filter.isEmpty()) {
            for (Post i : posts) {
                if (i.getTitleWorkout().equals(filter)) {
                    filterPost.add(i);
                }
            }
            return filterPost;
        }else {
            return posts;
        }
    }

    /**
     * @return all posts
     */
    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    /**
     * @return saved post
     *
     * @param titleWorkout
     * @param workoutDay
     * @param descriptionWorkout
     * @param durationOfTraining
     * @param filename
     * @param user
     */
    @Override
    public Post blogPostAdd(String titleWorkout, String workoutDay,
                            String descriptionWorkout, int durationOfTraining,String filename, User user) {
        Post post = new Post(titleWorkout, workoutDay, descriptionWorkout, durationOfTraining, filename, user);
        return postRepository.save(post);
    }

    /**
     * @return details posts by id
     *
     * @param id
     */
    @Override
    public List<Post> blogPostDetails(Long id) {
        Optional<Post> post = postRepository.findById(id);
        List<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        return res;
    }

    /**
     * @return edited posts by id
     *
     * @param id
     */
    @Override
    public List<Post> blogEditInfo(Long id) {
        Optional<Post> post = postRepository.findById(id);
        List<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        return res;
    }

    /**
     * @return updated post
     *
     * @param titleWorkout
     * @param workoutDay
     * @param descriptionWorkout
     * @param durationOfTraining
     * @param fileName
     */
    @Override
    public Post blogPostUpdate(Long id, String titleWorkout, String workoutDay, String descriptionWorkout, int durationOfTraining, String fileName) {
        Post post = postRepository.findById(id).orElseThrow(() -> new NoSuchPostException("There is no post with id = " + id));
        post.setTitleWorkout(titleWorkout);
        post.setWorkoutDay(workoutDay);
        post.setDescriptionWorkout(descriptionWorkout);
        post.setDurationOfTraining(durationOfTraining);
        post.setFileName(fileName);
        return postRepository.save(post);
    }

    /**
     * remove posts by id
     *
     * @param id
     */
    @Override
    public void blogPostRemove(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new NoSuchPostException("There is no post with id = " + id));
        postRepository.delete(post);
    }
}
