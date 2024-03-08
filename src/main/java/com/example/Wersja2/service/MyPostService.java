package com.example.Wersja2.service;

import com.example.Wersja2.dto.MyPostDTO;
import com.example.Wersja2.dto.MyUserDTO;
import com.example.Wersja2.module.MyPost;
import com.example.Wersja2.module.MyPostTag;
import com.example.Wersja2.module.MyUser;
import com.example.Wersja2.others.ElementNotFoundException;
import com.example.Wersja2.repository.MyPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MyPostService {
    private final MyPostRepository postRepository;

    @Autowired
    public MyPostService(MyPostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public MyPost savePost(MyPost post) {
        return postRepository.save(post);
    }

    public List<MyPost> getAllPosts() {
        return postRepository.findAll();
    }
    public MyPost getPostById(Long id) {
        return postRepository.findById(id).orElseThrow(() ->
                new ElementNotFoundException("Post with id " + id + " not found"));
    }
    public MyPostDTO getPostDtoById(Long id) {
        MyPost post = postRepository.findById(id).orElseThrow(() ->
                new ElementNotFoundException("Post with id " + id + " not found"));

        return mapToDto(post);
    }

    private MyPostDTO mapToDto(MyPost post) {
        MyPostDTO postDto = new MyPostDTO();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());
        postDto.setDate(post.getDate());
        postDto.setUser(mapUserToDto(post.getUser()));
        postDto.setTags(post.getTags().stream().map(MyPostTag::getName).collect(Collectors.toList()));

        return postDto;
    }

    private MyUserDTO mapUserToDto(MyUser user) {
        MyUserDTO userDto = new MyUserDTO();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());

        return userDto;
    }


}
