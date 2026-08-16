package com.example.demo.dto;

public class PostCreateRequest {
    private String title;
    private String content;
    private String author;

    public PostCreateRequest() {}

    public String getTitle() { return title; }
    public String getContent() { return content; }
    public String getAuthor() { return author; }
}