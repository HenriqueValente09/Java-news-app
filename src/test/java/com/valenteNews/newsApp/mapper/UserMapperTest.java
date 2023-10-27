package com.valenteNews.newsApp.mapper;

import com.valenteNews.newsApp.dto.user.RegisterUserDTO;
import com.valenteNews.newsApp.dto.user.UserDTO;
import com.valenteNews.newsApp.model.User;
import com.valenteNews.newsApp.model.UserRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {

    private final User user = User.builder()
            .name("Pedro")
            .email("pedro@gmail.com")
            .role(UserRole.ADMIN)
            .id(UUID.randomUUID().toString())
            .build();

    private final RegisterUserDTO userDTO = RegisterUserDTO.builder()
            .name("Pedro")
            .email("pedro@gmail.com")
            .build();

    @Test
    void userToUserDTO() {
        UserDTO userDTO = UserMapper.INSTANCE.userToUserDTO(user);
        assertEquals(userDTO.getId(), user.getId());
        assertEquals(userDTO.getName(), user.getName());
        assertEquals(userDTO.getEmail(), user.getEmail());
        assertEquals(userDTO.getRole(), user.getRole().toString());
    }

    @Test
    void usersToUserDTO() {
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            User user = User.builder()
                    .name("Pedro")
                    .email("pedro@gmail.com")
                    .role(UserRole.ADMIN)
                    .id(UUID.randomUUID().toString())
                    .build();
            userList.add(user);
        }
        List<UserDTO> userDTOList = UserMapper.INSTANCE.UsersToUserDTO(userList);

        int i = 0;

        for (UserDTO userDTO : userDTOList) {
            assertEquals(userDTO.getId(), userList.get(i).getId());
            i++;
        }
    }

    @Test
    void registerUserDTOToUser() {
        User user = UserMapper.INSTANCE.RegisterUserDTOToUser(userDTO);
    }
}