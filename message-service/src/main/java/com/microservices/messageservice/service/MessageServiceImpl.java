package com.microservices.messageservice.service;

import com.microservices.messageservice.dto.MessageDto;
import com.microservices.messageservice.entity.MessageEntity;
import com.microservices.messageservice.repository.MessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class MessageServiceImpl implements MessageService {
    private MessageRepository messageRepository;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Transactional
    @Override
    public MessageDto send(MessageDto dto) {
        log.info("Message Service's Service Layer :: Call send Method!");

        MessageEntity messageEntity = MessageEntity.builder()
                                                   .sender(dto.getSender())
                                                   .receiver(dto.getReceiver())
                                                   .content(dto.getContent())
                                                   .createdAt(LocalDateTime.now())
                                                   .build();

        messageRepository.save(messageEntity);

        return MessageDto.builder()
                         .sender(dto.getSender())
                         .receiver(dto.getReceiver())
                         .content(dto.getContent())
                         .build();
    }

    @Transactional
    @Override
    public Iterable<MessageDto> getUserList(String receiver) {
        log.info("Message Service's Service Layer :: Call getUserList Method!");

        Iterable<MessageEntity> messageList = messageRepository.findUserList(receiver);
        List<MessageDto> messages = new ArrayList<>();

        messageList.forEach(message -> {
            messages.add(MessageDto.builder()
                                   .id(message.getId())
                                   .sender(message.getSender())
                                   .receiver(message.getReceiver())
                                   .content(message.getContent())
                                   .createdAt(message.getCreatedAt())
                                   .build());
        });

        return messages;
    }

    @Transactional
    @Override
    public Iterable<MessageDto> getMessageList(
        String sender,
        String receiver
    ) {
        log.info("Message Service's Service Layer :: Call getMessageList Method!");

        Iterable<MessageEntity> messageList = messageRepository.findMessageList(sender,
                                                                               receiver);
        List<MessageDto> messages = new ArrayList<>();

        messageList.forEach(message -> {
            messages.add(MessageDto.builder()
                                   .id(message.getId())
                                   .sender(message.getSender())
                                   .receiver(message.getReceiver())
                                   .content(message.getContent())
                                   .createdAt(message.getCreatedAt())
                                   .build());
        });

        return messages;
    }
}
