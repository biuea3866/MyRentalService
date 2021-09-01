package com.microservices.authservice.dto;

import com.microservices.authservice.vo.ResponsePost;
import com.microservices.authservice.vo.ResponseRental;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    private String email;
    private String password;
    private String nickname;
    private String phoneNumber;
    private String userId;
    private String encryptedPwd;
    private List<ResponsePost> posts;
    private List<ResponseRental> rentals;

    @Builder
    public UserDto(
        String email,
        String password,
        String nickname,
        String phoneNumber,
        String userId,
        String encryptedPwd,
        List<ResponsePost> posts,
        List<ResponseRental> rentals
    ) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
        this.userId = userId;
        this.encryptedPwd = encryptedPwd;
        this.posts = posts;
        this.rentals = rentals;
    }
}
