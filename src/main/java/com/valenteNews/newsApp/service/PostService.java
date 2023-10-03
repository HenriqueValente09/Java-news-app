package com.valenteNews.newsApp.service;



import com.valenteNews.newsApp.model.Post;
import com.valenteNews.newsApp.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Post save(Post post) {
        try{
            return postRepository.save(post);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Optional<Post> findById(String id) {
        return postRepository.findById(id);
    }
}
