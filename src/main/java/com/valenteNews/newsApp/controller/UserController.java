package com.valenteNews.newsApp.controller;

import com.valenteNews.newsApp.dto.post.RegisterPostDTO;
import com.valenteNews.newsApp.dto.user.RegisterUserDTO;
import com.valenteNews.newsApp.dto.user.UserDTO;
import com.valenteNews.newsApp.mapper.UserMapper;
import com.valenteNews.newsApp.model.User;
import com.valenteNews.newsApp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/users")
    public String users(Model model) {
        List<UserDTO> userList = userMapper.UsersToUserDTO(userService.getAllUsers());
        model.addAttribute("users", userList);
        return "users";
    }

    @GetMapping("/register")
    public String processRegistration(Model model) {
        RegisterUserDTO user = new RegisterUserDTO();
        model.addAttribute("user", user);
        return "registerUser";
    }
}
