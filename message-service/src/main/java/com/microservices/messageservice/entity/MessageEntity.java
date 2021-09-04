package com.microservices.messageservice.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name="messages")
@NoArgsConstructor
public class MessageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String sender;

    @Column(nullable = false)
    private String receiver;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String createdAt;

    @Column(nullable = false)
    private String status;

    @Builder
    public MessageEntity(
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
