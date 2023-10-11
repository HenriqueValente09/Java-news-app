package com.valenteNews.newsApp.mapper;

import com.valenteNews.newsApp.dto.post.PostDTO;
import com.valenteNews.newsApp.dto.post.RegisterPostDTO;
import com.valenteNews.newsApp.model.Post;
import com.valenteNews.newsApp.model.User;
import com.valenteNews.newsApp.service.UserService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public abstract class PostMapper {
    UserMapper INSTANCE = Mappers.getMapper( UserMapper.class );

    @Autowired
    private UserService userService;

    @Mapping(target = "id", source = "id")
    public abstract PostDTO postToPostDTO(Post post);

    @Mapping(target = "id", source = "id")
    public abstract Post postDTOtoPost(PostDTO postDTO);

    @Mapping(target = "id", source = "id")
    public abstract List<PostDTO> postsToPostDTO(List<Post> posts);

    @Mapping(target = "user", expression = "java(getUserById(postDTO.getUserId()))")
    public abstract Post registerPostDTOtoPost(RegisterPostDTO postDTO);

    User getUserById(String userId) {
        Optional<User> user = userService.getUserById(userId);
        if (user.isPresent()){
            return user.get();
        }
        return null;
    }
}
