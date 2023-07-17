package com.sparta.post;


import com.sparta.post.dto.CommentRequestDto;
import com.sparta.post.dto.PostRequestDto;
import com.sparta.post.entity.Comment;
import com.sparta.post.entity.Post;
import com.sparta.post.entity.User;
import com.sparta.post.repository.CommentRepository;
import com.sparta.post.repository.PostRepository;
import com.sparta.post.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class CommentTest {

    CommentRepository commentRepository;
    UserRepository userRepository;
    PostRepository postRepository;


    @Autowired
    public CommentTest(CommentRepository commentRepository, UserRepository userRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @Test
    public void commentTest(){
        CommentRequestDto requestDto = new CommentRequestDto();
        requestDto.setContent("댓글 테스트입니다.");

        //아이디 가져오기
        User user = userRepository.findByUsername("user").orElseThrow(
                ()->new NullPointerException("아이디가 업습니다."));

        //포스트 작성
        Post post = new Post(new PostRequestDto("제목1","1234","내용1"));

        post.setUser(user);
        postRepository.save(post);

        //댓글 작성
        Comment comment = new Comment(requestDto);
        comment.setUser(user);
        comment.setPost(post);
        commentRepository.save(comment);

        //댓글 조회하기
        Comment comment1 = commentRepository.findById(1L).orElseThrow(
                () -> new NullPointerException("해당하는 댓글이 없습니다.")
        );

        System.out.println("comment.getContent() = " + comment1.getContent());
        System.out.println("comment.getUser().getUsername() = " + comment1.getUser().getUsername());
        System.out.println("comment.getPost().getContent() = " + comment1.getPost().getContent());

    }

}
