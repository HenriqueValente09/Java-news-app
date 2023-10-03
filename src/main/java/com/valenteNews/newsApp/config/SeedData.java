package com.valenteNews.newsApp.config;

import com.valenteNews.newsApp.model.Post;
import com.valenteNews.newsApp.model.User;
import com.valenteNews.newsApp.repository.UserRepository;
import com.valenteNews.newsApp.service.PostService;
import com.valenteNews.newsApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class SeedData implements CommandLineRunner {
    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

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

        List<Post> posts = postService.getAllPosts();
        if (posts.size() == 0) {
            Post post1 = new Post();
            post1.setTitle("Top 5 Bonecas Monster High");
            post1.setContent("Lorem Ipsum");
            post1.setImageURL("https://upload.wikimedia.org/wikipedia/pt/e/e5/MonsterHigh_Characters.png");
            Optional<User> user = userService.getUserByName("João");
            post1.setCreatedAt(LocalDateTime.now());
            post1.setUser(user.get());
            postService.save(post1);
        }
    }
}
