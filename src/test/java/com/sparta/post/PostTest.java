package com.sparta.post;

import com.sparta.post.dto.PostResponseDto;
import com.sparta.post.service.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
public class PostTest {

    @Autowired
    PostService postService;

    @Test
    public void getPostsTest(){
        List<PostResponseDto> posts = postService.getPosts();
        System.out.println(posts.get(0).getCommentList().get(0).getContent());
        System.out.println(posts.get(0).getUserName());
    }
}
