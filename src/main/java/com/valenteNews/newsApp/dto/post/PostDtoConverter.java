package com.valenteNews.newsApp.dto.post;

import com.valenteNews.newsApp.dto.user.UserDTO;
import com.valenteNews.newsApp.dto.user.UserDtoConverter;
import com.valenteNews.newsApp.model.Post;
import com.valenteNews.newsApp.model.User;
import com.valenteNews.newsApp.service.PostService;
import com.valenteNews.newsApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PostDtoConverter{

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserDtoConverter userDtoConverter;

    public PostDTO toDTO(Post post) {
        PostDTO postDTO = new PostDTO();
        postDTO.setTitle(post.getTitle());
        postDTO.setContent(post.getContent());
        postDTO.setImageURL(post.getImageURL());
        postDTO.setCreatedAt(post.getCreatedAt());
        postDTO.setUser(userDtoConverter.toDTO(post.getUser()));
        return postDTO;
    }

    public Post toPost(PostDTO postDTO) {
        Post post = new Post();
        post.setTitle(postDTO.getTitle());
        return post;
    }

    public Post registerToPost(RegisterPostDTO postDTO) {
        Post post = new Post();
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        post.setImageURL(postDTO.getImageURL());
        Optional<User> user = userService.getUserById(postDTO.getUserId());
        if (user.isPresent()) {
            post.setUser(user.get());
        }
        return post;
    }

}
