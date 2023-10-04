package com.valenteNews.newsApp.controller;

import com.valenteNews.newsApp.dto.post.PostDTO;
import com.valenteNews.newsApp.dto.post.PostDtoConverter;
import com.valenteNews.newsApp.model.Post;
import com.valenteNews.newsApp.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/post")
    public String getPost(@RequestParam String id, Model model){
        Optional<Post> optionalPost = postService.findById(id);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            PostDtoConverter converter = new PostDtoConverter();
            PostDTO postDTO = converter.toDTO(post);
            model.addAttribute("post", postDTO);

            return "post";
        }

        return "404";
    }

    @GetMapping("/register-post")
    public String registerPost(Model model){
        return "register-post";
    }
}
