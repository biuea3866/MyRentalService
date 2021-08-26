package com.microservices.postservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class CommentDto {
    String writer;

    String comment;

    String createdAt;

    String postId;

    @Builder
    public CommentDto(
        String writer,
        String comment,
        String createdAt,
        String postId
    ) {
        this.writer = writer;
        this.comment = comment;
        this.createdAt = createdAt;
        this.postId = postId;
    }
}
