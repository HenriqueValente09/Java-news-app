package com.valenteNews.newsApp.controller;

import com.valenteNews.newsApp.dto.post.PostDTO;
import com.valenteNews.newsApp.mapper.PostMapper;
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

    private final PostMapper postMapper;

    @GetMapping("/")
    public String home(Model model) {
        List<PostDTO> posts = postMapper.postsToPostDTO(postService.getAllPosts());
        List<PostDTO> recentPosts = posts;

        recentPosts = recentPosts.stream()
                .sorted(Comparator.comparing(PostDTO::getCreatedAt).reversed())
                .collect(Collectors.toList());

        recentPosts = recentPosts.stream()
                .limit(3)
                .collect(Collectors.toList());

        if (recentPosts.size() < 3) {
            return "redirect:/register-post";
        }

        posts.removeAll(recentPosts);

        model.addAttribute("posts", posts);
        model.addAttribute("recentPosts", recentPosts);
        return "home";
    }
}
