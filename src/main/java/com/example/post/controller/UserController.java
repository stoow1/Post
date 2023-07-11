package com.example.post.controller;

import com.example.post.dto.LoginRequestDto;
import com.example.post.dto.ResponseDto;
import com.example.post.dto.SignupRequestDto;
import com.example.post.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/auth")
@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;

    @ResponseBody
    @PostMapping("/signup")
    public ResponseDto signup(@Valid @RequestBody SignupRequestDto signupRequestDto) {
        try {
            return userService.signup(signupRequestDto);
        } catch (Exception e) {
            return new ResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value());  // 예외 발생시 에러 내용, Httpstatus(400)을 리턴값으로 전달한다.
        }
    }

    @ResponseBody
    @PostMapping("/login")
    public ResponseDto login(@RequestBody LoginRequestDto loginRequestDto) {
        try {
            return userService.login(loginRequestDto);
        } catch (Exception e) {
            return new ResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value());
        }
    }
}