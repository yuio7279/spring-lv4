package com.sparta.post.dto;

import com.sparta.post.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CommentResponseDto {
    private Long id;
    private String content;
    private String username;
    private int commentLikesCount;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.username = comment.getUser().getUsername();
        this.commentLikesCount = comment.getCommentLikesList().size();
    }
}
