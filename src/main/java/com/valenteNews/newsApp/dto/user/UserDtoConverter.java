package com.valenteNews.newsApp.dto.user;

import com.valenteNews.newsApp.model.User;
import com.valenteNews.newsApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDtoConverter{

    @Autowired
    private UserService userService;

    public UserDTO toDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        return dto;
    }

    public User toUser(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        return user;
    }

}
