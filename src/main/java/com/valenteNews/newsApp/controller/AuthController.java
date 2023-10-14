package com.valenteNews.newsApp.controller;

import com.valenteNews.newsApp.dto.auth.LoginResponseDTO;
import com.valenteNews.newsApp.dto.user.AuthDTO;
import com.valenteNews.newsApp.dto.user.RegisterUserDTO;
import com.valenteNews.newsApp.mapper.UserMapper;
import com.valenteNews.newsApp.model.User;
import com.valenteNews.newsApp.model.UserRole;
import com.valenteNews.newsApp.repository.UserRepository;
import com.valenteNews.newsApp.service.TokenService;
import com.valenteNews.newsApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/login")
    public ResponseEntity login(@ModelAttribute AuthDTO data){
        System.out.println(data);
        var emailPassword = new UsernamePasswordAuthenticationToken(data.getEmail(), data.getPassword());
        var auth = this.authenticationManager.authenticate(emailPassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public String register(@ModelAttribute RegisterUserDTO data){
        System.out.println(data);
        if(this.repository.findByEmail(data.getEmail()) != null) return "404";

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.getPassword());
        User newUser = userMapper.RegisterUserDTOToUser(data);
        newUser.setPassword(encryptedPassword);
        newUser.setRole(UserRole.USER);

        this.repository.save(newUser);

        return "redirect:/users";
    }
}
