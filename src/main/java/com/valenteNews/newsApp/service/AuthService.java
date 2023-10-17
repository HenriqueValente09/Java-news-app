package com.valenteNews.newsApp.service;

import com.valenteNews.newsApp.model.User;
import com.valenteNews.newsApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.valenteNews.newsApp.config.exceptions.EmailNotFoundException;

import java.util.Optional;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(username);
        if (user.isPresent()){
            return user.get();
        }
        return null;
    }

    public UserDetails loadUserByEmail(String email) throws EmailNotFoundException {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()){
            return user.get();
        }
        return null;
    }
}
