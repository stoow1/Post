package com.example.post.service;

import com.example.post.dto.PostRequestDto;
import com.example.post.dto.PostResponseDto;
import com.example.post.entity.Post;
import com.example.post.jwt.JwtUtil;
import com.example.post.repository.PostRepository;
import io.jsonwebtoken.Claims;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final JwtUtil jwtUtil;

    @Transactional
    public PostResponseDto savePost(String token, PostRequestDto postRequestDto) {
        String token1 = jwtUtil.substringToken(token);
        boolean ValidateToken = jwtUtil.validateToken(token1);
        Claims info = jwtUtil.getUserInfoFromToken(token);
        String username = info.getSubject();

        if(ValidateToken){
                Post post = new Post(postRequestDto, username);
                Post savePost = postRepository.save(post);
                return new PostResponseDto(savePost);
        }
        return null;
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
    public PostResponseDto putPost(String token, Long id, PostRequestDto postRequestDto) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new RuntimeException("존재하지 않는 글입니다")
        );

        String ValidateToken = jwtUtil.substringToken(token);

        if(!jwtUtil.validateToken(ValidateToken)) {
            throw new IllegalArgumentException("Token Error");
        }

        Claims info = jwtUtil.getUserInfoFromToken(token);
        String username = info.getSubject();

        if(!username.equals(post.getName())) {
            throw new IllegalArgumentException("해당 게시글을 작성한 사용자가 아닙니다.");
        }

        post.update(postRequestDto);

        return new PostResponseDto(post);
    }

   public PostResponseDto DeletePost(String token, Long id, PostRequestDto postRequestDto) {
       Post post = postRepository.findById(id).orElseThrow(
               () -> new RuntimeException("존재하지 않는 글입니다")
       );

       String ValidateToken = jwtUtil.substringToken(token);

       if(!jwtUtil.validateToken(ValidateToken)) {
           throw new IllegalArgumentException("Token Error");
       }

       Claims info = jwtUtil.getUserInfoFromToken(token);
       String username = info.getSubject();

       if(!username.equals(post.getName())) {
           throw new IllegalArgumentException("해당 게시글을 작성한 사용자가 아닙니다.");
       }

       postRepository.delete(post);
       return null;
    }
}