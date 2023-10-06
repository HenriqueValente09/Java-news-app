package com.valenteNews.newsApp.controller;

import com.valenteNews.newsApp.dto.post.PostDTO;
import com.valenteNews.newsApp.dto.post.PostDtoConverter;
import com.valenteNews.newsApp.dto.post.RegisterPostDTO;
import com.valenteNews.newsApp.dto.user.UserDTO;
import com.valenteNews.newsApp.dto.user.UserDtoConverter;
import com.valenteNews.newsApp.model.Post;
import com.valenteNews.newsApp.model.User;
import com.valenteNews.newsApp.service.PostService;
import com.valenteNews.newsApp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final UserService userService;
    private final UserDtoConverter userDtoConverter;
    private final PostDtoConverter postDtoConverter;

    @GetMapping("/post")
    public String getPost(@RequestParam String id, Model model){
        System.out.println(id);
        Optional<Post> optionalPost = postService.findById(id);
        System.out.println(optionalPost);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            PostDTO postDTO = postDtoConverter.toDTO(post);
            model.addAttribute("post", post);
            System.out.println(postDTO);
            return "post";
        }

        return "404";
    }

    @GetMapping("/register-post")
    public String registerPost(Model model){
        RegisterPostDTO post = new RegisterPostDTO();
        List<UserDTO> users = new ArrayList<>();
        for (User user : userService.getAllUsers()) {
            users.add(userDtoConverter.toDTO(user));
        }
        model.addAttribute("users", users);
        model.addAttribute("post", post);
        return "register-post";
    }

    @PostMapping("/create-post")
    public String createPost(@ModelAttribute("post") RegisterPostDTO postDTO) {
        postService.save(postDtoConverter.registerToPost(postDTO));
        return "redirect:/";
    }
}
