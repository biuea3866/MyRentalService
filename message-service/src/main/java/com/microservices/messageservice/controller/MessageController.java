package com.microservices.messageservice.controller;

import com.microservices.messageservice.dto.MessageDto;
import com.microservices.messageservice.service.MessageService;
import com.microservices.messageservice.vo.RequestSend;
import com.microservices.messageservice.vo.ResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
@Slf4j
public class MessageController {
    private MessageService messageService;
    private Environment env;

    @Autowired
    public MessageController(
        MessageService messageService,
        Environment env
    ) {
        this.messageService = messageService;
        this.env = env;
    }

    @GetMapping("/health_check")
    public String status() {
        return String.format(
            "It's working in Post Service"
                + ", port(local.server.port) =" + env.getProperty("local.server.port")
                + ", port(server.port) =" + env.getProperty("server.port")
        );
    }

    @PostMapping("/send")
    public ResponseEntity<?> send(@RequestBody RequestSend vo) {
        log.info("Message Service's Controller Layer :: Call send Method!");

        MessageDto messageDto = MessageDto.builder()
                                          .sender(vo.getSender())
                                          .receiver(vo.getReceiver())
                                          .content(vo.getContent())
                                          .build();

        return ResponseEntity.status(HttpStatus.CREATED).body("Successfully send message to " + messageService.send(messageDto).getReceiver());
    }

    @GetMapping("/{id}/get-message")
    public ResponseEntity<?> getMessageById(@PathVariable("id") Long id) {
        log.info("Message Service's Controller Layer :: getMessageById send Method!");

        MessageDto messageDto = messageService.getMessageById(id);

        return ResponseEntity.status(HttpStatus.OK).body(ResponseMessage.builder()
                                                                        .sender(messageDto.getSender())
                                                                        .receiver(messageDto.getReceiver())
                                                                        .content(messageDto.getContent())
                                                                        .createdAt(messageDto.getCreatedAt())
                                                                        .build());
    }

    @GetMapping("/{nickname}/send-list")
    public ResponseEntity<?> getAllSendList(@PathVariable("nickname") String nickname) {
        log.info("Message Service's Controller Layer :: getSendList send Method!");

        Iterable<MessageDto> messageList = messageService.getAllSendList(nickname);
        List<ResponseMessage> messages = new ArrayList<>();

        messageList.forEach(message -> {
            messages.add(ResponseMessage.builder()
                                        .id(message.getId())
                                        .sender(message.getSender())
                                        .receiver(message.getReceiver())
                                        .content(message.getContent())
                                        .createdAt(message.getCreatedAt())
                                        .build());
        });

        return ResponseEntity.status(HttpStatus.OK).body(messages);
    }

    @GetMapping("/{nickname}/receive-list")
    public ResponseEntity<?> getAllReceiveList(@PathVariable("nickname") String nickname) {
        log.info("Message Service's Controller Layer :: getAllReceiveList send Method!");

        Iterable<MessageDto> messageList = messageService.getAllReceiveList(nickname);
        List<ResponseMessage> messages = new ArrayList<>();

        messageList.forEach(message -> {
            messages.add(ResponseMessage.builder()
                                        .id(message.getId())
                                        .sender(message.getSender())
                                        .receiver(message.getReceiver())
                                        .content(message.getContent())
                                        .createdAt(message.getCreatedAt())
                                        .build());
        });

        return ResponseEntity.status(HttpStatus.OK).body(messages);
    }

    @PostMapping("/{id}/delete-message")
    public ResponseEntity<?> deleteMessage(@PathVariable("id") Long id) {
        log.info("Message Service's Controller Layer :: deleteMessage send Method!");

        messageService.deleteMessage(id);

        return ResponseEntity.status(HttpStatus.OK).body("Successfully delete the message!");
    }
}
