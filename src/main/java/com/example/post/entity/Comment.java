package com.example.post.entity;

import com.example.post.dto.CommentRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Comment extends TimeStamped {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private String username;

    @ManyToOne                                            // Comment(many) <-> Post(one) Join
    @JoinColumn(name = "Post_Id", nullable = false)       // Post Primary key값을 가져와서 매핑시킴
    private Post post;


    public Comment(CommentRequestDto commentRequestDto, Post post) {
        this.contents = commentRequestDto.getContents();         // 사용자가 입력한 댓글 내용
        this.username = commentRequestDto.getUsername();         // 사용자 ID
        this.post = post;                                 // Post 컬럼 데이터
    }

    public void update(CommentRequestDto commentRequestDt) {
        this.contents = commentRequestDt.getContents();         // 사용자가 입력한 댓글 내용
        this.username = commentRequestDt.getUsername();         // 사용자 ID
    }
}