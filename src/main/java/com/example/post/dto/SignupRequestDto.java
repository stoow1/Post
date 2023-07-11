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
    @Pattern(regexp = "[a-z0-9]{4,10}")
    private String username;

    @NotBlank(message = "비밀번호를 입력해주세요")
    @Pattern(regexp = "[a-zA-Z0-9]{8,15}")
    private String password;
}