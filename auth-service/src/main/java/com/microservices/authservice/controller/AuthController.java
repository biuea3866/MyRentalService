package com.microservices.authservice.controller;

import com.microservices.authservice.dto.UserDto;
import com.microservices.authservice.service.AuthService;
import com.microservices.authservice.vo.RequestRegister;
import com.microservices.authservice.vo.ResponseUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@Slf4j
public class AuthController {
    private AuthService authService;
    private Environment env;

    @Autowired
    public AuthController(
        AuthService authService,
        Environment env
    ) {
        this.authService = authService;
        this.env = env;
    }

    @GetMapping("/health_check")
    public String status() {
        return String.format(
            "It's working in Auth Service"
            + ", port(local.server.port) =" + env.getProperty("local.server.port")
            + ", port(server.port) =" + env.getProperty("server.port")
            + ", token secret = " + env.getProperty("token.secret")
            + ", token expiration time = " + env.getProperty("token.exp_time")
        );
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

    @GetMapping("/info/{userId}")
    public ResponseEntity<?> getUser(@PathVariable("userId") String userId) {
        log.info("Auth Service's Controller Layer :: Call getUser Method!");

        UserDto userDto = authService.getUser(userId);

        return ResponseEntity.status(HttpStatus.OK).body(ResponseUser.builder()
                                                                     .email(userDto.getEmail())
                                                                     .nickname(userDto.getNickname())
                                                                     .phoneNumber(userDto.getPhoneNumber())
                                                                     .userId(userDto.getUserId())
                                                                     .posts(userDto.getPosts())
                                                                     .build());
    }

    @GetMapping("/info/{nickname}/my-rental-list")
    public ResponseEntity<?> getMyRentals(@PathVariable("nickname") String nickname) {
        log.info("Auth Service's Controller Layer :: Call getMyRentals Method!");

        UserDto userDto = authService.getRentalsByNickname(nickname);

        return ResponseEntity.status(HttpStatus.OK).body(ResponseUser.builder()
                                                                     .rentals(userDto.getRentals())
                                                                     .build());
    }

    @GetMapping("/info/{nickname}/my-borrow-list")
    public ResponseEntity<?> getMyBorrow(@PathVariable("nickname") String nickname) {
        log.info("Auth Service's Controller Layer :: Call getMyRentals Method!");

        UserDto userDto = authService.getBorrowsByNickname(nickname);

        return ResponseEntity.status(HttpStatus.OK).body(ResponseUser.builder()
                                                                     .rentals(userDto.getRentals())
                                                                     .build());
    }

    @GetMapping("/info/{userId}/check")
    public ResponseEntity<?> check(@PathVariable("userId") String userId) {
        log.info("Auth Service's Controller Layer :: Call getMyRentals Method!");

        if(userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not user");
        }

        UserDto userDto = authService.getUser(userId);

        return ResponseEntity.status(HttpStatus.OK).body(ResponseUser.builder()
                                                                     .email(userDto.getEmail())
                                                                     .nickname(userDto.getNickname())
                                                                     .phoneNumber(userDto.getPhoneNumber())
                                                                     .userId(userDto.getUserId())
                                                                     .posts(userDto.getPosts())
                                                                     .build());
    }

    @GetMapping("/check/nickname/{nickname}")
    public ResponseEntity<?> checkNickname(@PathVariable("nickname") String nickname) {
        log.info("Auth Service's Controller Layer :: Call checkNickname Method!");

        if(authService.checkNickname(nickname)) {
            return ResponseEntity.status(HttpStatus.OK).body(false);
        }

        return ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @GetMapping("/check/email/{email}")
    public ResponseEntity<?> checkEmail(@PathVariable("email") String email) {
        log.info("Auth Service's Controller Layer :: Call checkNickname Method!");

        if(authService.checkEmail(email)) {
            return ResponseEntity.status(HttpStatus.OK).body(false);
        }

        return ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        log.info("Auth Service's Controller Layer :: Call logout Method!");

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Successfully logout");
    }
}
