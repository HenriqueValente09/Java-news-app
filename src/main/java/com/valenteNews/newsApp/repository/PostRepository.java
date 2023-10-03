package com.valenteNews.newsApp.repository;

import com.valenteNews.newsApp.model.Post;
import com.valenteNews.newsApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Optional<Post> findById(String id);
}
