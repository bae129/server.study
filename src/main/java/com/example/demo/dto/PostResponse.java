package com.example.demo.dto;

import com.example.demo.entity.Post;

public class PostResponse {
    private Long id;
    private String title;
    private String content;
    private String author;

    public PostResponse(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.author = post.getUser().getNickname();
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public String getAuthor() { return author; }
}