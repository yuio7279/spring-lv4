package com.sparta.post.service;

import com.sparta.post.dto.CommentRequestDto;
import com.sparta.post.entity.*;
import com.sparta.post.repository.CommentLikesRepository;
import com.sparta.post.repository.CommentRepository;
import com.sparta.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final CommentLikesRepository commentLikesRepository;


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
        if(user.getRole().equals(UserRoleEnum.ADMIN)){
            foundedComment.update(requestDto);
        }
        else{
            if(foundedComment.getUser().getUsername().equals(user.getUsername())){
                foundedComment.update(requestDto);
            }else{
                throw new IllegalArgumentException("작성자만 수정할 수 있습니다.");
            }
        }

    }

    public void deleteComment(Long commentId,User user) {
        Comment foundedComment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("댓글이 존재하지 않습니다.")
        );
        if(user.getRole().equals(UserRoleEnum.ADMIN)){
            commentRepository.delete(foundedComment);
        }else {
            if (foundedComment.getUser().getUsername().equals(user.getUsername())) {
                commentRepository.delete(foundedComment);
            } else {
                throw new IllegalArgumentException("작성자만 삭제할 수 있습니다.");
            }
        }
    }

    public void likeComment(Long id, User user) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new NullPointerException("댓글을 찾을 수 없습니다.")
        );
        if(commentLikesRepository.findByUserAndComment(user, comment).isPresent()){
            throw new IllegalArgumentException("이미 좋아요를 누르셨습니다.");

        }
        CommentLikes commentLikes = commentLikesRepository.save(new CommentLikes(user, comment));
    }

    public void deleteLikeComment(Long id, User user){
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new NullPointerException("댓글을 찾을 수 없습니다.")
        );
        if(commentLikesRepository.findByUserAndComment(user, comment).isEmpty()){
            throw new IllegalArgumentException("이미 삭제되었습니다.");

        }
        Optional<CommentLikes> like = commentLikesRepository.findByUserAndComment(user, comment);
        commentLikesRepository.delete(like.get());
    }
}
