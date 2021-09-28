package com.microservices.messageservice.repository;

import com.microservices.messageservice.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, Long> {
    @Query(
        value = "SELECT distinct (sender, content, createdAt) " +
                "FROM messages m " +
                "WHERE m.receiver = :receiver " +
                "ORDER BY m.id DESC ",
        nativeQuery = true
    )
    Iterable<MessageEntity> findUserList(String receiver);

    @Query(
        value = "SELECT * " +
            "FROM messages m " +
            "WHERE m.receiver = :receiver AND m.sender = :sender " +
            "ORDER BY m.id ASC",
        nativeQuery = true
    )
    Iterable<MessageEntity> findMessageList(
        String sender,
        String receiver
    );
}
