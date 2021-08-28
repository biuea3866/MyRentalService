package com.microservices.postservice.vo;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class RequestCreateComment {
    @NotNull()
    String writer;

    @NotNull(message="Comment cannot be null")
    String comment;

    @NotNull(message="postId cannot be null")
    Long postId;

    String createdAt;
}
