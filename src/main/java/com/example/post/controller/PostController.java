package com.example.post.controller;


import com.example.post.dto.DeletePostDto;
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

    private  final PostService postService;

    @Transactional
    @PostMapping("/post")
    public PostResponseDto savePost(@RequestBody PostRequestDto postRequestDto){
        return postService.savePost(postRequestDto);
    }

    @Transactional
    @GetMapping("/posts")
    public List<PostResponseDto> getPosts(){

        return postService.getPosts();
    }

    @Transactional
    @GetMapping("/post/{id}")
    public PostResponseDto getPost(@PathVariable Long id){

        return postService.getPost(id);
    }

    @Transactional
    @PutMapping("/post/{id}")
    public PostResponseDto putPost(@PathVariable Long id, @RequestBody PostRequestDto postRequestDto){
        return postService.putPost(id, postRequestDto);
    }

    @Transactional
    @DeleteMapping("/post/{id}")
    public DeletePostDto deletePost(@PathVariable Long id, @RequestBody PostRequestDto postRequestDto){
        return postService.deletePost(id, postRequestDto);
    }
}////
