package com.microservices.rentalservice.vo;

import lombok.Getter;

@Getter
public class RequestComplete {
    private String rentalId;

    private boolean acceptance;
}
