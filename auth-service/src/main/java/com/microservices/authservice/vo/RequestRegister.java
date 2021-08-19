package com.microservices.authservice.vo;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
public class RequestRegister {
    @NotNull(message="Email cannot be null")
    @Size(min=2, message="Email not be less than 2 characters")
    private String email;

    @NotNull(message="Password cannot be null")
    @Size(min=6, message="Password must be equal or than 6 characters")
    private String password;

    @NotNull(message="Nickname cannot be null")
    @Size(min=2, message="Nickname not be less than 2 characters")
    private String nickname;

    @NotNull(message="Phone cannot be null")
    private String phoneNumber;
}
