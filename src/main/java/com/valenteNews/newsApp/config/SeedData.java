package com.valenteNews.newsApp.config;

import com.valenteNews.newsApp.model.Post;
import com.valenteNews.newsApp.model.User;
import com.valenteNews.newsApp.service.PostService;
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
            post1.setTitle("AAAAAAAAAAAAA");
            post1.setContent("Lorem Ipsum");
            post1.setImageURL("https://cdn1.epicgames.com/b30b6d1b4dfd4dcc93b5490be5e094e5/offer/RDR2476298253_Epic_Games_Wishlist_RDR2_2560x1440_V01-2560x1440-2a9ebe1f7ee202102555be202d5632ec.jpg");
            Optional<User> user = userService.getUserByName("João");
            post1.setUser(user.get());
            postService.save(post1);
        }
    }
}
