package com.microservices.messageservice.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseMessage {
    private Long id;
    private String sender;
    private String receiver;
    private String content;
    private String createdAt;

    @Builder
    public ResponseMessage(
        Long id,
        String sender,
        String receiver,
        String content,
        String createdAt
    ) {
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.createdAt = createdAt;
    }
}
