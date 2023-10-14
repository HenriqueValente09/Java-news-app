package com.valenteNews.newsApp.dto.user;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
public class UserDTO {
    private String id;
    private String name;
    private String email;
}
