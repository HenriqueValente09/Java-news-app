package com.valenteNews.newsApp.dto.user;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class RegisterUserDTO {
    private String name;
    private String email;
    private String password;
}
