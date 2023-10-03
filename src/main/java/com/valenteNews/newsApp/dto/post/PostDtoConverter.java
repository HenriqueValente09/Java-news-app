package com.valenteNews.newsApp.dto.post;

import com.valenteNews.newsApp.dto.user.UserDTO;
import com.valenteNews.newsApp.model.Post;
import com.valenteNews.newsApp.model.User;
import com.valenteNews.newsApp.service.PostService;
import com.valenteNews.newsApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostDtoConverter{

    @Autowired
    private PostService postService;

    public PostDTO toDTO(Post post) {
        PostDTO postDTO = new PostDTO();
        postDTO.setTitle(post.getTitle());
        postDTO.setContent(post.getContent());
        postDTO.setImageURL(post.getImageURL());
        postDTO.setCreatedAt(post.getCreatedAt());
        return postDTO;
    }

    public User toPost(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        return user;
    }

}
