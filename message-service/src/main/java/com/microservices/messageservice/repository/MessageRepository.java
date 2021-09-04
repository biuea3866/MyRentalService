package com.microservices.messageservice.repository;

import com.microservices.messageservice.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, Long> {

    MessageEntity findMessageById(Long id);

    @Query(
        value = "SELECT * " +
                "FROM messages m " +
                "WHERE m.status = 'CREATE_MESSAGE' AND m.sender = :sender",
        nativeQuery = true
    )
    Iterable<MessageEntity> findAllSendList(String sender);

    @Query(
        value = "SELECT * " +
                "FROM messages m " +
                "WHERE m.status = 'CREATE_MESSAGE' AND m.receiver = :receiver",
        nativeQuery = true
    )
    Iterable<MessageEntity> findAllReceiveList(String receiver);

    @Query(
        value = "UPDATE messages m " +
                "SET m.status = 'DELETE_MESSAGE' " +
                "WHERE m.id = :id",
        nativeQuery = true
    )
    void updateStatus(Long id);
}
