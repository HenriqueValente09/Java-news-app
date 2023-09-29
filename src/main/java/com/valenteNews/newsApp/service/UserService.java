package com.valenteNews.newsApp.service;

import com.valenteNews.newsApp.dto.UserDTO;
import com.valenteNews.newsApp.model.User;
import com.valenteNews.newsApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User save(User user) {
        try{
            return userRepository.save(user);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
