package com.microservices.postservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class CommentDto {
    String writer;

    String comment;

    String createdAt;

    Long postId;

    @Builder
    public CommentDto(
        String writer,
        String comment,
        String createdAt,
        Long postId
    ) {
        this.writer = writer;
        this.comment = comment;
        this.createdAt = createdAt;
        this.postId = postId;
    }
}
