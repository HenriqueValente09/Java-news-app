package com.valenteNews.newsApp.dto.post;

import com.valenteNews.newsApp.dto.user.UserDTO;
import com.valenteNews.newsApp.model.User;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class PostDTO {
    private String id;
    private String title;
    private String content;
    private String imageURL;
    private LocalDateTime createdAt;
    private UserDTO user;

}
