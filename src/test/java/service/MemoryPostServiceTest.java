/*
package service;

import com.sparta.post.dto.PostRequestDto;
import com.sparta.post.dto.PostResponseDto;
import com.sparta.post.entity.Post;
import com.sparta.post.service.PostService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import com.sparta.post.repository.PostRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MemoryPostServiceTest {

    @Test
    void getPosts() {
        //given
        PostService postService = new PostService(new PostRepository());
        postService.createPost(new PostRequestDto("title", "유저네임", "1234", "content"));
        postService.createPost(new PostRequestDto("title2", "유저네임2", "1234", "content2"));

        //when
        List<PostResponseDto> posts = postService.getPosts();

        //then
        Assertions.assertThat(posts.size()).isEqualTo(2);

    }

    @Test
    void createPost() {
        //1. 테스트용 클래스
        //mocking
        PostService postService = new PostService(new PostRepository());

        PostResponseDto post = postService.createPost(new PostRequestDto("title", "유저네임", "1234", "content"));

        Assertions.assertThat(post.getId()).isEqualTo(1L);
    }

    @Test
    void getPostOne(){
        PostService postService = new PostService(new PostRepository());
        postService.createPost(new PostRequestDto("title", "유저네임", "1234", "content"));
        postService.createPost(new PostRequestDto("title2", "유저네임2", "1234", "content2"));
        Long id = 2L;

        Post post =  postService.getPostOne(id);
        PostResponseDto postResponseDto = new PostResponseDto(post);

        Assertions.assertThat(postResponseDto.getId()).isEqualTo(id);
    }

    @Test
    void deletePost(){
        PostService postService = new PostService(new PostRepository());
        postService.createPost(new PostRequestDto("title", "유저네임", "1234", "content"));
        postService.createPost(new PostRequestDto("title2", "유저네임2", "1234", "content2"));
        Long id = 2L;
//        String password1 = "1233";
        String password2 = "1234";

        PostResponseDto postResponseDto = postService.deletePost(id, password2);

        Assertions.assertThat(postResponseDto.getMsg()).isEqualTo(id+"번 글 삭제가 완료되었습니다.");
    }

    @Test
    void updatePost(){
        PostService postService = new PostService(new PostRepository());
        postService.createPost(new PostRequestDto("title", "유저네임", "1234", "content"));
        postService.createPost(new PostRequestDto("title2", "유저네임2", "1234", "content2"));
        Long id = 2L;
        PostRequestDto updatePost = new PostRequestDto("title_update", "유저네임2_update", "1234", "content2_update");

        PostResponseDto updatedPostResponseDto = postService.updatePost(id, updatePost);

        Assertions.assertThat(updatedPostResponseDto.getId()).isEqualTo(id);


    }
}*/
