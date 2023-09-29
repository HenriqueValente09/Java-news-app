package com.valenteNews.newsApp.controller;

import com.valenteNews.newsApp.dto.UserDTO;
import com.valenteNews.newsApp.dto.UserDtoConverter;
import com.valenteNews.newsApp.model.User;
import com.valenteNews.newsApp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserDtoConverter userDtoConverter;

    @GetMapping("/")
    public String home(Model model) {
        Optional<User> user2 = userService.getUserByName("João");
        UserDTO userDTO = new UserDTO();
        model.addAttribute("user", userDtoConverter.toDTO(user2.get()));
        model.addAttribute("userDTO", userDTO);
        return "home";
    }

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
