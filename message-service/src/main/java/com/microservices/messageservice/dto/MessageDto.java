package com.microservices.messageservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class MessageDto {
    private Long id;
    private String sender;
    private String receiver;
    private String content;
    private String createdAt;
    private String status;

    @Builder
    public MessageDto(
        Long id,
        String sender,
        String receiver,
        String content,
        String createdAt,
        String status
    ) {
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.createdAt = createdAt;
        this.status = status;
    }
}
