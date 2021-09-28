package com.microservices.messageservice.service;

import com.microservices.messageservice.dto.MessageDto;

public interface MessageService {
    MessageDto send(MessageDto dto);

    Iterable<MessageDto> getUserList(String receiver);

    Iterable<MessageDto> getMessageList(String sender,
                                        String receiver);
}
