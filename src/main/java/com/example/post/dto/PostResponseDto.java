package com.example.post.dto;

import com.example.post.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class PostResponseDto {
    private Long id;
    private String title;
    private String content;
    private String name;
    private LocalDateTime createdat;
    private LocalDateTime modifiedat;

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.name = post.getName();
        this.createdat = post.getCreatedAt();
        this.modifiedat = post.getModifiedAt();

    }
}
