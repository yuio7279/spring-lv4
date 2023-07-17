package com.sparta.post.service;

import com.sparta.post.dto.DeleteRequestDto;
import com.sparta.post.dto.PostRequestDto;
import com.sparta.post.dto.PostResponseDto;
import com.sparta.post.entity.Post;
import com.sparta.post.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.sparta.post.repository.PostRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.InputMismatchException;
import java.util.List;

@Service
@Slf4j
public class PostService {

    PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<PostResponseDto> getPosts(){
        return postRepository.findAllByOrderByModifiedAtDesc().stream().map(PostResponseDto::new).toList();

    }
    public PostResponseDto createPost(PostRequestDto postRequestDto, User user){

        //작성자명 세팅
        postRequestDto.setUserName(user.getUsername());
        Post post = new Post(postRequestDto);

        //Entity로 변환
        Post savePost = postRepository.save(post);
        PostResponseDto postResponseDto = new PostResponseDto(savePost);
        return postResponseDto;
    }

    public Post getPostOne(Long id){
        return postRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("선택한 게시글이 없습니다.")
        );

    }

    public PostResponseDto deletePost(Long id, DeleteRequestDto deleteRequestDto, User user){
        Post post = getPostOne(id);
        String postUsername = post.getUserName();
        if(!postUsername.equals(user.getUsername())){
            throw new IllegalArgumentException("작성자 본인이 아닙니다.");
        }

        if(post.getPassword().equals(deleteRequestDto.getPassword())){
            postRepository.delete(post);
            PostResponseDto postResponseDto = new PostResponseDto(post);
            postResponseDto.setMsg(id+"번 글 삭제가 완료되었습니다.");
            return postResponseDto;
        }else{
            throw new InputMismatchException("비밀번호가 올바르지 않습니다.");
        }
    }

    @Transactional
    public PostResponseDto updatePost(Long id, PostRequestDto postRequestDto, User user){
        Post post = getPostOne(id);
        String postUsername = post.getUserName();
        if(!postUsername.equals(user.getUsername())){
            throw new IllegalArgumentException("작성자 본인이 아닙니다.");
        }

        if(post.getPassword().equals(postRequestDto.getPassword())){
            postRequestDto.setUserName(user.getUsername());
            post.update(postRequestDto);
            PostResponseDto postResponseDto = new PostResponseDto(post);
            return postResponseDto;
        }else{
            throw new InputMismatchException("비밀번호가 올바루지 않습니다.");
        }
    }
}
