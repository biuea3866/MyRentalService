package com.microservices.authservice.vo;

import lombok.Builder;
import lombok.Data;

@Data
public class ResponseUser {
    private String email;
    private String nickname;
    private String phoneNumber;
    private String userId;
    private String encryptedPwd;

    @Builder
    public ResponseUser(
        String email,
        String nickname,
        String phoneNumber,
        String userId,
        String encryptedPwd
    ) {
        this.email = email;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
        this.userId = userId;
        this.encryptedPwd = encryptedPwd;
    }
}
