package com.microservices.messageservice.service;

import com.microservices.messageservice.dto.MessageDto;

public interface MessageService {
    MessageDto send(MessageDto dto);

    MessageDto getMessageById(Long id);

    Iterable<MessageDto> getAllSendList(String sender);

    Iterable<MessageDto> getAllReceiveList(String receiver);

    void deleteMessage(Long id);
}
