package com.valenteNews.newsApp.dto.user;

import lombok.*;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserDTO {
    private String name;
    private String email;
    private String password;
}
