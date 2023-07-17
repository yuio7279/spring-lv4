package com.sparta.post.service;

import com.sparta.post.dto.CommentRequestDto;
import com.sparta.post.entity.Comment;
import com.sparta.post.entity.Post;
import com.sparta.post.entity.User;
import com.sparta.post.repository.CommentRepository;
import com.sparta.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;


    public void createComment(Long postId, CommentRequestDto requestDto, User user) {

        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );
        Comment comment = new Comment(requestDto);
        comment.setUser(user);
        comment.setPost(post);
        commentRepository.save(comment);
    }

    @Transactional
    public void updateComment(Long commentId, CommentRequestDto requestDto, User user) {

        Comment foundedComment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("댓글이 존재하지 않습니다.")
        );

        if(foundedComment.getUser().getUsername().equals(user.getUsername())){
            foundedComment.update(requestDto);
        }else{
            throw new IllegalArgumentException("작성자만 수정할 수 있습니다.");
        }
    }

    public void deleteComment(Long commentId,User user) {
        Comment foundedComment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("댓글이 존재하지 않습니다.")
        );

        if(foundedComment.getUser().getUsername().equals(user.getUsername())){
            commentRepository.delete(foundedComment);
        }else{
            throw new IllegalArgumentException("작성자만 삭제할 수 있습니다.");
        }
    }
}
