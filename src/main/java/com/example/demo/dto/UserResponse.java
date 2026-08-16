package com.example.demo.dto;

import com.example.demo.entity.User;

public class UserResponse {
    private Long id;
    private String username;
    private String nickname;

    public UserResponse(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.nickname = user.getNickname();
    }

    public Long getId() { return id; }
    public String getUsername() { return username; }
    public String getNickname() { return nickname; }
}