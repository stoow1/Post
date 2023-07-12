package com.example.post.service;

import com.example.post.dto.CommentRequestDto;
import com.example.post.dto.CommentResponseDto;
import com.example.post.dto.ResponseDto;
import com.example.post.entity.Comment;
import com.example.post.entity.Post;
import com.example.post.entity.User;
import com.example.post.jwt.JwtUtil;
import com.example.post.repository.CommentRepository;
import com.example.post.repository.PostRepository;
import com.example.post.repository.UserRepository;
import io.jsonwebtoken.Claims;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

// Post Comment Create Function
@Service
public class CommentService {
    private PostRepository postRepository;
    private CommentRepository commentRepository;
    private JwtUtil jwtUtil;
    private UserRepository userRepository;

    public CommentService(PostRepository postRepository, CommentRepository commentRepository, JwtUtil jwtUtil) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.jwtUtil = jwtUtil;
    }

    public CommentResponseDto createComment(Long id, CommentRequestDto requestDto, String token) {
        String token1 = jwtUtil.substringToken(token);

        if (!jwtUtil.validateToken(token1)) {
            throw new IllegalArgumentException("토큰이 유효하지 않습니다.");
        }

        Post post = findPost(requestDto.getId());
        Claims info = jwtUtil.getUserInfoFromToken(token1);

        String username = info.getSubject();
        Comment comment = new Comment(requestDto, post);

        Comment saveComment = commentRepository.save(comment);
        return new CommentResponseDto(saveComment);
    }

    @Transactional
    public CommentResponseDto updateComment(Long id,CommentRequestDto commentRequestDto,String token) {
        Comment comment = findComment(id);

        String token1 = jwtUtil.substringToken(token);

        if (!jwtUtil.validateToken(token1)) {
            throw new IllegalArgumentException("토큰이 유효하지 않습니다.");
        }

        Claims info = jwtUtil.getUserInfoFromToken(token1);
        String username = info.getSubject();
        checkAuthority(comment, username);

        comment.update(commentRequestDto);

        return new CommentResponseDto(comment);
    }


    public ResponseDto deleteComment(Long id,String token) {
        Comment comment = findComment(id);

        String token1 = jwtUtil.substringToken(token);

        if (!jwtUtil.validateToken(token1)) {
            throw new IllegalArgumentException("토큰이 유효하지 않습니다.");
        }

        Claims info = jwtUtil.getUserInfoFromToken(token);
        String username = info.getSubject();
        checkAuthority(comment, username);

        commentRepository.delete(comment);

        return new ResponseDto("삭제완료", HttpStatus.OK.value());
    }

    private Comment findComment(Long id) {
        return commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("선택하신 댓글은 존재하지 않습니다.")
        );
    }

    private Post findPost(Long id) {
        return postRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 포스트는 존재하지 않습니다.")
        );
    }

    public void checkAuthority(Comment comment, String username) {
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("사용자를 찾을 수 없습니다.")
        );
        if(!user.getRole().checkAuthority().equals("ROLE_ADMIN")){
            if (!comment.getUsername().equals(user.getUsername())) {
                throw new IllegalArgumentException("본인이 아닙니다");
            }
        }
    }
}