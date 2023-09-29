package com.valenteNews.newsApp.config;

import com.valenteNews.newsApp.model.User;
import com.valenteNews.newsApp.repository.UserRepository;
import com.valenteNews.newsApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class SeedData implements CommandLineRunner {
    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {

        List<User> users = userService.getAllUsers();
        if (users.size() == 0) {
            User user1 = new User();
            user1.setName("João");

            User user2 = new User();
            user2.setName("Matheus");

            userService.save(user1);
            userService.save(user2);
        }


    }
}
