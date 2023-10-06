package com.valenteNews.newsApp.controller;

import com.valenteNews.newsApp.dto.post.PostDTO;
import com.valenteNews.newsApp.dto.user.UserDtoConverter;
import com.valenteNews.newsApp.model.Post;
import com.valenteNews.newsApp.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final PostService postService;
    private final UserDtoConverter userDtoConverter;

    @GetMapping("/")
    public String home(Model model) {
        List<Post> recentPosts = postService.getAllPosts();

        recentPosts = recentPosts.stream()
                .sorted(Comparator.comparing(Post::getCreatedAt).reversed())
                .collect(Collectors.toList());

        recentPosts = recentPosts.stream()
                .limit(3)
                .collect(Collectors.toList());

        if (recentPosts.size() < 3) {
            return "redirect:/register-post";
        }

        List<PostDTO> recentPostsDTO = new ArrayList<>();

        for (Post post : recentPosts) {
            PostDTO postDTO = new PostDTO();
            postDTO.setId(post.getId());
            postDTO.setTitle(post.getTitle());
            postDTO.setContent(post.getContent());
            postDTO.setImageURL(post.getImageURL());

            recentPostsDTO.add(postDTO);
        }

        model.addAttribute("posts", postService.getAllPosts());
        model.addAttribute("recentPosts", recentPostsDTO);
        return "home";
    }
}
