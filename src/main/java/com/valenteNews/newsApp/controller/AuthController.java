package com.valenteNews.newsApp.controller;

import com.valenteNews.newsApp.dto.user.AuthDTO;
import com.valenteNews.newsApp.dto.user.RegisterUserDTO;
import com.valenteNews.newsApp.mapper.UserMapper;
import com.valenteNews.newsApp.model.User;
import com.valenteNews.newsApp.model.UserRole;
import com.valenteNews.newsApp.repository.UserRepository;
import com.valenteNews.newsApp.service.TokenService;
import com.valenteNews.newsApp.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository repository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();

    @PostMapping("/login")
    public String login(@ModelAttribute AuthDTO data, HttpServletResponse response, Principal principal, HttpSession session){
        var emailPassword = new UsernamePasswordAuthenticationToken(data.getEmail(), data.getPassword());
        var auth = this.authenticationManager.authenticate(emailPassword);

        User user = (User) auth.getPrincipal();
        session.setAttribute("user", user.getEmail());
        session.setAttribute("user_role", user.getRole());
        System.out.println(user.getName());
        System.out.println(session.getId());
        return "redirect:/users";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        System.out.println(authentication);
        this.logoutHandler.logout(request, response, authentication);
        return "redirect:/home";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute RegisterUserDTO data){
        if(this.repository.findByEmail(data.getEmail()) != null) return "404";

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.getPassword());
        User newUser = userMapper.RegisterUserDTOToUser(data);
        newUser.setPassword(encryptedPassword);
        newUser.setRole(UserRole.USER);

        this.repository.save(newUser);

        return "redirect:/users";
    }
}
