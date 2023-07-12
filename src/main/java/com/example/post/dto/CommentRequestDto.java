package com.example.post.dto;

import lombok.Getter;

@Getter
public class CommentRequestDto {
    private Long id;
    private String contents;
    private String username;
}