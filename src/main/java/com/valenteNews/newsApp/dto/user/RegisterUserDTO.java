package com.valenteNews.newsApp.dto.user;

import lombok.Data;

@Data
public class RegisterUserDTO {
    private String username;
    private String email;
    private String password;

}