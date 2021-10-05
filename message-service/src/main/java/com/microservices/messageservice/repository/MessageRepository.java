package com.microservices.messageservice.repository;

import com.microservices.messageservice.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, Long> {
    @Query(value = "SELECT distinct sender, receiver " +
                   "FROM messages m " +
                   "WHERE m.sender = :sender OR m.receiver = :sender " +
                   "ORDER BY m.id DESC",
           nativeQuery = true)
    Iterable<Object[]> findUserList(String sender);

    @Query(value = "SELECT * " +
                   "FROM messages m " +
                   "WHERE (m.receiver = :receiver AND m.sender = :sender) OR (m.receiver = :sender AND m.sender = :receiver)" +
                   "ORDER BY m.id ASC",
           nativeQuery = true)
    Iterable<MessageEntity> findMessageList(
        String sender,
        String receiver
    );

    @Query(
        value = "DELETE " +
                "FROM messages m " +
                "WHERE m.receiver = :receiver AND m.sender = :sender",
        nativeQuery = true
    )
    void deleteBySenderAndReceiver(
        String receiver,
        String sender
    );
}
