package com.sparta.post.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequestDto {

    private String title;
    private String userName;
    private String password;
    private String content;

    public PostRequestDto(String title, String password, String content) {

        this.title = title;
        this.password = password;
        this.content = content;
    }
}
