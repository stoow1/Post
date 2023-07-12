package com.example.post.entity;

import com.example.post.dto.PostRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    @OrderBy("createdAt desc")
    private List<Comment> commentList = new ArrayList<>();

    public Post(PostRequestDto postRequestDto, String name) {
        this.name = name;
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