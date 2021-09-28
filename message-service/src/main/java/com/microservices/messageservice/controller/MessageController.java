package com.microservices.messageservice.controller;

import com.microservices.messageservice.dto.MessageDto;
import com.microservices.messageservice.service.MessageService;
import com.microservices.messageservice.vo.RequestMessageList;
import com.microservices.messageservice.vo.RequestSend;
import com.microservices.messageservice.vo.RequestUserList;
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

    @GetMapping("/user-list")
    public ResponseEntity<?> getUserList(@RequestBody RequestUserList vo) {
        log.info("Message Service's Controller Layer :: Call getUserList Method!");

        Iterable<MessageDto> messageList = messageService.getUserList(vo.getReceiver());
        List<ResponseMessage> result = new ArrayList<>();

        messageList.forEach(v -> {
            result.add(ResponseMessage.builder()
                                      .id(v.getId())
                                      .sender(v.getSender())
                                      .receiver(v.getReceiver())
                                      .content(v.getContent())
                                      .createdAt(v.getCreatedAt())
                                      .build()
            );
        });

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/message-list")
    public ResponseEntity<?> getMessageList(@RequestBody RequestMessageList vo) {
        log.info("Message Service's Controller Layer :: Call getUserList Method!");

        Iterable<MessageDto> messageList = messageService.getMessageList(vo.getSender(),
                                                                         vo.getReceiver());
        List<ResponseMessage> result = new ArrayList<>();

        messageList.forEach(v -> {
            result.add(ResponseMessage.builder()
                .id(v.getId())
                .sender(v.getSender())
                .receiver(v.getReceiver())
                .content(v.getContent())
                .createdAt(v.getCreatedAt())
                .build()
            );
        });

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
