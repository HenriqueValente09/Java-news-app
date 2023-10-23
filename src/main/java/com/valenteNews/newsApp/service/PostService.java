package com.valenteNews.newsApp.service;



import com.valenteNews.newsApp.dto.post.PostDTO;
import com.valenteNews.newsApp.dto.user.UserDTO;
import com.valenteNews.newsApp.mapper.PostMapper;
import com.valenteNews.newsApp.model.Post;
import com.valenteNews.newsApp.model.User;
import com.valenteNews.newsApp.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostMapper postMapper;

    public Post save(Post post) {
        try{
            post.setCreatedAt(LocalDateTime.now());
            return postRepository.save(post);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public Void delete(Post post) {
        try{
            postRepository.delete(post);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Optional<Post> findById(String id) {
        return postRepository.findById(id);
    }

    public Page<PostDTO> findPaginated(Pageable pageable, List<PostDTO> posts) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<PostDTO> list;

        if (posts.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, posts.size());
            list = posts.subList(startItem, toIndex);
        }

        Page<PostDTO> postPage
                = new PageImpl<PostDTO>(list, PageRequest.of(currentPage, pageSize), posts.size());

        return postPage;
    }
}
