package com.sparta.post.controller;

import com.sparta.post.dto.CommentRequestDto;
import com.sparta.post.security.UserDetailsImpl;
import com.sparta.post.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/api")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    //댓글 조회는 필요가없다 게시물에서 댓글리스트로 나갈테니

    //댓글 작성
    @PostMapping("/comments/{postId}")
    public RedirectView createComment(RedirectAttributes attributes, @PathVariable Long postId, @RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        commentService.createComment(postId, requestDto, userDetails.getUser());
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/api/posts");
        return redirectView;
    }

    @PutMapping("/comments/{commentId}")
    public RedirectView updateComment(RedirectAttributes attributes, @PathVariable Long commentId, @RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        commentService.updateComment(commentId, requestDto, userDetails.getUser());
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/api/posts");
        return redirectView;
    }

    @DeleteMapping("/comments/{commentId}")
    public RedirectView deleteComment(
            RedirectAttributes attributes,
            @PathVariable Long commentId,
            @AuthenticationPrincipal UserDetailsImpl userDetails){

        commentService.deleteComment(commentId, userDetails.getUser());
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/api/posts");
        return redirectView;

    }




}
