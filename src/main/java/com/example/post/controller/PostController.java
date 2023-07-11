package com.example.post.controller;


import com.example.post.dto.PostRequestDto;
import com.example.post.dto.PostResponseDto;
import com.example.post.service.PostService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @Transactional
    @PostMapping("/post")
    public PostResponseDto savePost(@RequestHeader("Authorization") String token, @RequestBody PostRequestDto postRequestDto) {
        return postService.savePost(token, postRequestDto);
    }

    @Transactional
    @GetMapping("/posts")
    public List<PostResponseDto> getPosts() {

        return postService.getPosts();
    }

    @Transactional
    @GetMapping("/post/{id}")
    public PostResponseDto getPost(@PathVariable Long id) {

        return postService.getPost(id);
    }

    @Transactional
    @PutMapping("/post/{id}")
    public PostResponseDto putPost(@RequestHeader("Authorization") String token, @PathVariable Long id, @RequestBody PostRequestDto postRequestDto) {
        return postService.putPost(token, id, postRequestDto);
    }

    @Transactional
    @DeleteMapping("/post/{id}")
    public PostResponseDto deletePost(@RequestHeader("Authorization") String token, @PathVariable Long id, @RequestBody PostRequestDto postRequestDto) {
        return postService.DeletePost(token, id, postRequestDto);
    }

}