package com.valenteNews.newsApp.controller;

import com.valenteNews.newsApp.dto.user.UserDTO;
import com.valenteNews.newsApp.dto.user.UserDtoConverter;
import com.valenteNews.newsApp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserDtoConverter userDtoConverter;

    @GetMapping("/users")
    public String users(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @PostMapping("/register")
    public String processRegistration(@ModelAttribute("user") UserDTO userDTO) {
        userService.save(userDtoConverter.toUser(userDTO));
        return "redirect:/";
    }
}
