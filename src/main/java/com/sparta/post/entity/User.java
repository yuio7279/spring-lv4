package com.sparta.post.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    //user는 여러개의 post를 가질 수 있음
    @OneToMany(mappedBy = "user")
    List<Post> postList = new ArrayList<>();

    //user는 여러개의 comment를 가질 수 있음
    @OneToMany(mappedBy = "user")
    List<Comment> commentList = new ArrayList<>();


    public void addPostList(Post post){
        this.postList.add(post);
        post.setUser(this);
    }

    public User(String username, String password, String email, UserRoleEnum role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }
}
