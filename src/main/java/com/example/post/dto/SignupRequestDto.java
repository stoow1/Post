package com.example.post.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class SignupRequestDto {

    @NotBlank(message = "아이디를 입력해주세요")
    @Pattern(regexp = "^[a-z0-9]{4,10}$")
    private String username;

    @NotBlank(message = "비밀번호를 입력해주세요")
    @Pattern(regexp = "^[A-Za-z0-9!@#$%^&*\\])(?=.]{8,15}$")
    private String password;

    private boolean admin = false;      // 일반사용자(default)=false, 관리자(=true)
    private String adminToken = "";     // admin=true로 반환했을 경우 adminToken값 확인 일치하면 Admin 처리
}