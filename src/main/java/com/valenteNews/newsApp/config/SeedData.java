package com.valenteNews.newsApp.config;

import com.valenteNews.newsApp.model.User;
import com.valenteNews.newsApp.repository.UserRepository;
import com.valenteNews.newsApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SeedData implements CommandLineRunner {
    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        User user1 = new User();
        user1.setName("João");

        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");

        userService.save(user1);

    }
}
