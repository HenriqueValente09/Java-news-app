package com.valenteNews.newsApp.controller;

import com.valenteNews.newsApp.dto.auth.LoginResponseDTO;
import com.valenteNews.newsApp.dto.post.PostDTO;
import com.valenteNews.newsApp.dto.post.RegisterPostDTO;
import com.valenteNews.newsApp.dto.user.AuthDTO;
import com.valenteNews.newsApp.dto.user.RegisterUserDTO;
import com.valenteNews.newsApp.dto.user.UserDTO;
import com.valenteNews.newsApp.mapper.PostMapper;
import com.valenteNews.newsApp.mapper.UserMapper;
import com.valenteNews.newsApp.model.Post;
import com.valenteNews.newsApp.model.User;
import com.valenteNews.newsApp.service.PostService;
import com.valenteNews.newsApp.service.TokenService;
import com.valenteNews.newsApp.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;
    private final PostService postService;
    private final PostMapper postMapper;

    @GetMapping("/users")
    public String users(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        List<UserDTO> userList = userMapper.UsersToUserDTO(userService.getAllUsers());
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);

        Page<UserDTO> userPage = userService.findPaginated(PageRequest.of(currentPage - 1, pageSize));
        model.addAttribute("userPage", userPage);
        int totalPages = userPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        model.addAttribute("users", userList);
        return "users";
    }

    @GetMapping("/user-posts")
    public String users(Model model, @RequestParam("userId") String userId, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        Optional<User> optionalUser = userService.getUserById(userId);
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);

        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            List<PostDTO> postList = postMapper.postsToPostDTO(user.getPosts());
            Page<PostDTO> posts = postService.findPaginated(PageRequest.of(currentPage - 1, pageSize), postList);
            int totalPages = posts.getTotalPages();
            if (totalPages > 0) {
                List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                        .boxed()
                        .collect(Collectors.toList());
                model.addAttribute("pageNumbers", pageNumbers);
            }

            model.addAttribute("posts", posts);

            return "user-posts";
        }
        return "404";
    }

    @GetMapping("/register")
    public String processRegistration(Model model) {
        RegisterUserDTO user = new RegisterUserDTO();
        model.addAttribute("user", user);
        return "registerUser";
    }

    @GetMapping("/login")
    public String login(Model model) {
        AuthDTO user = new AuthDTO();
        model.addAttribute("user", user);
        return "loginUser";
    }
}
