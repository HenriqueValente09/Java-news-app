package com.valenteNews.newsApp.service;

import com.valenteNews.newsApp.dto.user.UserDTO;
import com.valenteNews.newsApp.mapper.UserMapper;
import com.valenteNews.newsApp.model.User;
import com.valenteNews.newsApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public Page<UserDTO> findPaginated(Pageable pageable) {
        List<User> userList = userRepository.findAll();
        List<UserDTO> users = userMapper.UsersToUserDTO(userList);
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<UserDTO> list;

        if (users.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, users.size());
            list = users.subList(startItem, toIndex);
        }

        Page<UserDTO> userPage
                = new PageImpl<UserDTO>(list, PageRequest.of(currentPage, pageSize), users.size());

        return userPage;
    }

    public User save(User user) {
        try{
            return userRepository.save(user);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserByName(String name) {
        return userRepository.findByName(name);
    }

    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
