package com.example.post.service;

import com.example.post.dto.DeletePostDto;
import com.example.post.dto.PostRequestDto;
import com.example.post.dto.PostResponseDto;
import com.example.post.entity.Post;
import com.example.post.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public PostResponseDto savePost(PostRequestDto postRequestDto) {
        Post post = new Post(postRequestDto);
        Post savePost = postRepository.save(post);
        return new PostResponseDto(savePost);
    }

    public List<PostResponseDto> getPosts() {
        List<Post> postList = postRepository.findAllByOrderByModifiedAtDesc();
        List<PostResponseDto> postResponseDto = new ArrayList<>();
        for (Post post : postList) {
            PostResponseDto postResponseDto1 = new PostResponseDto(post);
            postResponseDto.add(postResponseDto1);
        }
        return postResponseDto;
    }

    public PostResponseDto getPost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new RuntimeException("존재하지 않는 글입니다")
        );
        return new PostResponseDto(post);
    }

    @Transactional
    public PostResponseDto putPost(Long id, PostRequestDto postRequestDto) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new RuntimeException("존재하지 않는 글입니다")
        );
        PostResponseDto postResponseDto = new PostResponseDto(post);

        if(postRequestDto.getPwd().equals(post.getPwd())) {
            post.update(postRequestDto);
            return postResponseDto;
        } else {
            return postResponseDto;
        }
    }

   public DeletePostDto deletePost(Long id, PostRequestDto postRequestDto) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new RuntimeException("존재하지 않는 글입니다")
        );
        DeletePostDto deletePostDto = new DeletePostDto();
        if (postRequestDto.getPwd().equals(post.getPwd())) {
            postRepository.deleteById(id);
            deletePostDto.setMsg("success");
        } else {
            deletePostDto.setMsg("fail");
        }
        return deletePostDto;
    }
}