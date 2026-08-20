package com.example.demo.dto;

import com.example.demo.entity.Comment;

public class CommentResponse {
    private Long id;
    private String content;
    private String author;
    private Long postId;

    public CommentResponse(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.author = comment.getUser().getNickname();
        this.postId = comment.getPost().getId();
    }

    public Long getId() { return id; }
    public String getContent() { return content; }
    public String getAuthor() { return author; }
    public Long getPostId() { return postId; }
}