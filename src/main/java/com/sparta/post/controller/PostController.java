package com.sparta.post.controller;

import com.sparta.post.dto.DeleteRequestDto;
import com.sparta.post.dto.PostRequestDto;
import com.sparta.post.dto.PostResponseDto;
import com.sparta.post.entity.Post;
import com.sparta.post.security.UserDetailsImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import com.sparta.post.service.PostService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts")
    public List<PostResponseDto> getPosts(){
        return postService.getPosts();
    }
    @GetMapping("/posts/{id}")
    public Post getPostOne(@PathVariable Long id){
        return postService.getPostOne(id);
    }

    @PostMapping("/posts")
    public PostResponseDto createPost(@RequestBody PostRequestDto postRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return postService.createPost(postRequestDto, userDetails.getUser());
    }

    @DeleteMapping("/posts/{id}")
    public PostResponseDto deletePost(@PathVariable Long id,@RequestBody DeleteRequestDto deleteRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return postService.deletePost(id, deleteRequestDto, userDetails.getUser());
    }

    @PutMapping("/posts/{id}")
    public PostResponseDto updatePost(@PathVariable Long id, @RequestBody PostRequestDto postRequestDto,@AuthenticationPrincipal UserDetailsImpl userDetails){
        return postService.updatePost(id, postRequestDto, userDetails.getUser());
    }
}
