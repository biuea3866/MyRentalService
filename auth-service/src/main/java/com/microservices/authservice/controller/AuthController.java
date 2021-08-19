package com.microservices.authservice.controller;

import com.microservices.authservice.dto.UserDto;
import com.microservices.authservice.service.AuthService;
import com.microservices.authservice.vo.RequestRegister;
import com.microservices.authservice.vo.ResponseUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@Slf4j
public class AuthController {
    private AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/health_check")
    public String status() {
        return "It's working in Auth Service";
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RequestRegister user) {
        log.info("Auth Service's Controller Layer :: Call register Method!");

        UserDto userDto = authService.registerUser(UserDto.builder()
                                                          .email(user.getEmail())
                                                          .password(user.getPassword())
                                                          .nickname(user.getNickname())
                                                          .phoneNumber(user.getPhoneNumber())
                                                          .build());

        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(ResponseUser.builder()
                                               .email(userDto.getEmail())
                                               .nickname(userDto.getNickname())
                                               .phoneNumber(userDto.getPhoneNumber())
                                               .userId(userDto.getUserId())
                                               .encryptedPwd(userDto.getEncryptedPwd())
                                               .build());
    }
}
