package com.valenteNews.newsApp.controller;

import com.valenteNews.newsApp.dto.auth.LoginResponseDTO;
import com.valenteNews.newsApp.dto.post.RegisterPostDTO;
import com.valenteNews.newsApp.dto.user.AuthDTO;
import com.valenteNews.newsApp.dto.user.RegisterUserDTO;
import com.valenteNews.newsApp.dto.user.UserDTO;
import com.valenteNews.newsApp.mapper.UserMapper;
import com.valenteNews.newsApp.model.User;
import com.valenteNews.newsApp.service.TokenService;
import com.valenteNews.newsApp.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;
    private final TokenService tokenService;

    @GetMapping("/users")
    public String users(Model model, HttpServletRequest request, Principal principal, HttpSession session) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        System.out.println(auth);
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

    @GetMapping("/login")
    public String login(Model model) {
        AuthDTO user = new AuthDTO();
        model.addAttribute("user", user);
        return "loginUser";
    }
}
