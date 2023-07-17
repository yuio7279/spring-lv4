package com.sparta.post.dto;

import com.sparta.post.entity.User;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignupResponseDto {
    private String username;
    private String password;
    private String email;
    private String msg;


    public SignupResponseDto(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.email = user.getEmail();
    }
}
