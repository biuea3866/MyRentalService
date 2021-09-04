package com.microservices.messageservice.service;

import com.microservices.messageservice.dto.MessageDto;
import com.microservices.messageservice.entity.MessageEntity;
import com.microservices.messageservice.repository.MessageRepository;
import com.microservices.messageservice.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
                                                   .createdAt(DateUtil.dateNow())
                                                   .status("CREATE_MESSAGE")
                                                   .build();

        messageRepository.save(messageEntity);

        return MessageDto.builder()
                         .sender(dto.getSender())
                         .receiver(dto.getReceiver())
                         .content(dto.getContent())
                         .createdAt(DateUtil.dateNow())
                         .status("CREATE_MESSAGE")
                         .build();
    }

    @Transactional
    @Override
    public MessageDto getMessageById(Long id) {
        log.info("Message Service's Service Layer :: Call getMessageById Method!");

        MessageEntity messageEntity = messageRepository.findMessageById(id);

        return MessageDto.builder()
                         .id(messageEntity.getId())
                         .sender(messageEntity.getSender())
                         .receiver(messageEntity.getReceiver())
                         .content(messageEntity.getContent())
                         .createdAt(messageEntity.getCreatedAt())
                         .build();
    }

    @Transactional
    @Override
    public Iterable<MessageDto> getAllSendList(String sender) {
        log.info("Message Service's Service Layer :: Call getAllSendList Method!");

        Iterable<MessageEntity> messageList = messageRepository.findAllSendList(sender);
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
    public Iterable<MessageDto> getAllReceiveList(String receiver) {
        log.info("Message Service's Service Layer :: Call getAllReceiveList Method!");

        Iterable<MessageEntity> messageList = messageRepository.findAllReceiveList(receiver);
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
    public void deleteMessage(Long id) {
        log.info("Message Service's Service Layer :: Call deleteMessage Method!");

        messageRepository.updateStatus(id);
    }
}
