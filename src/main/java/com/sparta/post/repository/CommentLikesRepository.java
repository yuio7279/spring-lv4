package com.sparta.post.repository;

import com.sparta.post.entity.Comment;
import com.sparta.post.entity.CommentLikes;
import com.sparta.post.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentLikesRepository extends JpaRepository<CommentLikes, Long> {

    Optional<CommentLikes> findByUserAndComment(User user, Comment comment);
}
