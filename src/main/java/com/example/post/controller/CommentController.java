package com.example.post.controller;

import com.example.post.dto.CommentRequestDto;
import com.example.post.dto.CommentResponseDto;
import com.example.post.dto.ResponseDto;
import com.example.post.jwt.JwtUtil;
import com.example.post.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{id}")
    public CommentResponseDto createComment(@PathVariable Long id, @RequestBody CommentRequestDto commentRequestDto, @RequestHeader(JwtUtil.AUTHORIZATION_HEADER) String token) {
        return commentService.createComment(id, commentRequestDto, token);
    }

    @PatchMapping("/{id}")
    public CommentResponseDto updateComment(@PathVariable Long id, @RequestBody CommentRequestDto commentRequestDto, @RequestHeader(JwtUtil.AUTHORIZATION_HEADER) String token) {
        return commentService.updateComment(id, commentRequestDto, token);
    }

    @DeleteMapping("/{id}")
    public ResponseDto deleteComment(@PathVariable Long id, @RequestHeader(JwtUtil.AUTHORIZATION_HEADER) String token) {
        return commentService.deleteComment(id, token);
    }
}
