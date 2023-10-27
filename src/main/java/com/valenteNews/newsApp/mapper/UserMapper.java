package com.valenteNews.newsApp.mapper;

import com.valenteNews.newsApp.dto.user.RegisterUserDTO;
import com.valenteNews.newsApp.dto.user.UserDTO;
import com.valenteNews.newsApp.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper( UserMapper.class );

    @Mapping(target = "id", source = "id")
    UserDTO userToUserDTO(User user);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "role", source = "role")
    List<UserDTO> UsersToUserDTO(List<User> users);

    User RegisterUserDTOToUser(RegisterUserDTO userDTO);
}
