package com.microservices.authservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class UserDto {
    private String email;
    private String password;
    private String nickname;
    private String phoneNumber;
    private String userId;
    private String encryptedPwd;

    @Builder
    public UserDto(
        String email,
        String password,
        String nickname,
        String phoneNumber,
        String userId,
        String encryptedPwd
    ) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
        this.userId = userId;
        this.encryptedPwd = encryptedPwd;
    }
}
