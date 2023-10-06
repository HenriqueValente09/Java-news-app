package com.valenteNews.newsApp.dto.post;

import com.valenteNews.newsApp.model.User;
import lombok.Data;

@Data
public class RegisterPostDTO {
    private String title;

    private String imageURL;

    private String content;

    private String userId;
}
