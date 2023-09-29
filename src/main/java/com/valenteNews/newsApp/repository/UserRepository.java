package com.valenteNews.newsApp.repository;

import com.valenteNews.newsApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    //Optional<User>  findById(String id);
    Optional<User>  findByName(String name);
}
