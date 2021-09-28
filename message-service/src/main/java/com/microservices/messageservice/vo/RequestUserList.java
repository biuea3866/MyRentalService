package com.microservices.messageservice.vo;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class RequestUserList {
    @NotNull(message="Receiver cannot be null")
    private String receiver;
}
