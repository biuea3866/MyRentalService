package com.microservices.authservice.vo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class ResponseUser {
    private String email;
    private String nickname;
    private String phoneNumber;
    private String userId;
    private String encryptedPwd;
    private List<ResponsePost> posts;
    private List<ResponseRental> rentals;

    @Builder
    public ResponseUser(
        String email,
        String nickname,
        String phoneNumber,
        String userId,
        String encryptedPwd,
        List<ResponsePost> posts,
        List<ResponseRental> rentals
    ) {
        this.email = email;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
        this.userId = userId;
        this.encryptedPwd = encryptedPwd;
        this.posts = posts;
        this.rentals = rentals;
    }
}
