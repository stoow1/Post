package com.example.post.entity;

import com.example.post.dto.PostRequestDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Post extends TimeStamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private String name;
    private String pwd;


    public Post(PostRequestDto postRequestDto, String username) {
        this.name = postRequestDto.getName();
        this.title = postRequestDto.getTitle();
        this.content = postRequestDto.getContent();
        this.pwd = postRequestDto.getPwd();
    }

    public void update(PostRequestDto postRequestDto) {
        this.name = postRequestDto.getName();
        this.content = postRequestDto.getContent();
        this.title = postRequestDto.getTitle();
    }
}