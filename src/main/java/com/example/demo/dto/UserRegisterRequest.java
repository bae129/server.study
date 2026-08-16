package com.example.demo.dto;

public class UserRegisterRequest {
    private String username;
    private String password;
    private String nickname;

    public UserRegisterRequest() {}

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getNickname() { return nickname; }
}