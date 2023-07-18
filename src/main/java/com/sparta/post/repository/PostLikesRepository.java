package com.sparta.post.repository;

import com.sparta.post.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostLikesRepository extends JpaRepository<PostLikes, Long> {
    Optional<PostLikes> findByUserAndPost(User user, Post post);
}
