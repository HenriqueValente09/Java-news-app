package com.valenteNews.newsApp.controller;

import com.valenteNews.newsApp.dto.post.PostDTO;
import com.valenteNews.newsApp.dto.post.RegisterPostDTO;
import com.valenteNews.newsApp.dto.user.UserDTO;
import com.valenteNews.newsApp.mapper.PostMapper;
import com.valenteNews.newsApp.mapper.UserMapper;
import com.valenteNews.newsApp.model.Post;
import com.valenteNews.newsApp.model.User;
import com.valenteNews.newsApp.service.PostService;
import com.valenteNews.newsApp.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final UserService userService;
    private final PostMapper postMapper;
    private final UserMapper userMapper;

    @GetMapping("/post")
    public String getPost(@RequestParam String id, Model model){
        Optional<Post> optionalPost = postService.findById(id);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            PostDTO postDTO = postMapper.postToPostDTO(post);
            model.addAttribute("post", postDTO);
            return "post";
        }
        return "404";
    }

    @GetMapping("/register-post")
    public String registerPost(Model model){
        RegisterPostDTO post = new RegisterPostDTO();
        model.addAttribute("post", post);
        return "register-post";
    }

    @PostMapping("/create-post")
    public String createPost(@ModelAttribute("post") RegisterPostDTO postDTO) {
        System.out.println(postDTO);
        postService.save(postMapper.registerPostDTOtoPost(postDTO));
        return "redirect:/";
    }

    @GetMapping("/delete-post")
    public RedirectView deletePost(@RequestParam String postId, HttpServletRequest request) {
        String referer = request.getHeader("Referer");
        Optional<Post> optionalPost = postService.findById(postId);
        if (optionalPost.isPresent()){
            postService.delete(optionalPost.get());
        }
        return new RedirectView(referer);
    }
}
