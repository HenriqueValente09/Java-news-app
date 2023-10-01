package com.valenteNews.newsApp.controller;

import com.valenteNews.newsApp.dto.user.UserDTO;
import com.valenteNews.newsApp.dto.user.UserDtoConverter;
import com.valenteNews.newsApp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final UserService userService;
    private final UserDtoConverter userDtoConverter;

    @GetMapping("/")
    public String home(Model model) {
        UserDTO userDTO = new UserDTO();
        model.addAttribute("userDTO", userDTO);
        return "home";
    }
}
