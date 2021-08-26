package com.microservices.postservice.vo;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class RequestCreateComment {
    @NotNull()
    String writer;

    @NotNull(message="Comment cannot be null")
    String comment;

    String createdAt;
}
