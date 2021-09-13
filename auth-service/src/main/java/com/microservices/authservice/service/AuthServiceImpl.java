package com.microservices.authservice.service;

import com.microservices.authservice.clients.PostClient;
import com.microservices.authservice.clients.RentalClient;
import com.microservices.authservice.dto.UserDto;
import com.microservices.authservice.entity.UserEntity;
import com.microservices.authservice.repository.AuthRepository;
import com.microservices.authservice.util.DateUtil;
import com.microservices.authservice.vo.ResponsePost;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {
    private AuthRepository authRepository;
    private BCryptPasswordEncoder passwordEncoder;
    private PostClient postClient;
    private RentalClient rentalClient;
    private CircuitBreakerFactory circuitBreakerFactory;

    @Autowired
    public AuthServiceImpl(
        AuthRepository authRepository,
        BCryptPasswordEncoder passwordEncoder,
        PostClient postClient,
        RentalClient rentalClient,
        CircuitBreakerFactory circuitBreakerFactory
    ) {
        this.authRepository = authRepository;
        this.passwordEncoder = passwordEncoder;
        this.postClient = postClient;
        this.rentalClient = rentalClient;
        this.circuitBreakerFactory = circuitBreakerFactory;
    }

    @Transactional
    @Override
    public UserDto registerUser(UserDto userDto) {
        log.info("Auth Service's Service Layer :: Call register Method!");

        UserEntity userEntity = UserEntity.builder()
                                          .email(userDto.getEmail())
                                          .nickname(userDto.getNickname())
                                          .phoneNumber(userDto.getPhoneNumber())
                                          .encryptedPwd(passwordEncoder.encode(userDto.getPassword()))
                                          .userId(UUID.randomUUID().toString())
                                          .createdAt(DateUtil.dateNow())
                                          .build();

        authRepository.save(userEntity);

        return userDto.builder()
                      .email(userEntity.getEmail())
                      .nickname(userEntity.getNickname())
                      .phoneNumber(userEntity.getPhoneNumber())
                      .encryptedPwd(userEntity.getEncryptedPwd())
                      .userId(userEntity.getUserId())
                      .build();
    }

    @Transactional
    @Override
    public UserDto getUserDetailsByEmail(String email) {
        log.info("Auth Service's Service Layer :: Call getUserDetailsByEmail Method!");

        UserEntity userEntity = authRepository.findByEmail(email);

        if(userEntity == null) throw new UsernameNotFoundException(email);

        return UserDto.builder()
                      .email(userEntity.getEmail())
                      .nickname(userEntity.getNickname())
                      .phoneNumber(userEntity.getPhoneNumber())
                      .userId(userEntity.getUserId())
                      .encryptedPwd(userEntity.getEncryptedPwd())
                      .build();
    }

    @Transactional
    @Override
    public UserDto getUser(String userId) {
        log.info("Auth Service's Service Layer :: Call getUser Method!");

        UserEntity userEntity = authRepository.findByUserId(userId);

        if(userEntity == null) throw new UsernameNotFoundException(userId);

        log.info("Before call post-service");

        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitbreaker");

        List<ResponsePost> postList = circuitBreaker.run(
            () -> postClient.getPosts(userId),
            throwable -> new ArrayList<>()
        );

        log.info("After called post-service");

        return UserDto.builder()
                      .email(userEntity.getEmail())
                      .nickname(userEntity.getNickname())
                      .phoneNumber(userEntity.getPhoneNumber())
                      .userId(userEntity.getUserId())
                      .encryptedPwd(userEntity.getEncryptedPwd())
                      .posts(postList)
                      .build();
    }

    @Transactional
    @Override
    public UserDto getRentalsByNickname(String nickname) {
        log.info("Auth Service's Service Layer :: Call getRentalsByNickname Method!");

        return UserDto.builder()
                      .rentals(rentalClient.getRentalsByOwner(nickname))
                      .build();
    }

    @Transactional
    @Override
    public UserDto getBorrowsByNickname(String nickname) {
        log.info("Auth Service's Service Layer :: Call getBorrowsByNickname Method!");

        return UserDto.builder()
                      .rentals(rentalClient.getRentalsByBorrower(nickname))
                      .build();
    }

    @Transactional
    @Override
    public boolean checkNickname(String nickname) {
        log.info("Auth Service's Service Layer :: Call checkNickname Method!");

        return authRepository.existsByNickname(nickname);
    }

    @Transactional
    @Override
    public boolean checkEmail(String email) {
        log.info("Auth Service's Service Layer :: Call checkEmail Method!");

        return authRepository.existsByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = authRepository.findByEmail(email);

        if(userEntity == null) throw new UsernameNotFoundException(email);

        return new User(
            userEntity.getEmail(),
            userEntity.getEncryptedPwd(),
            true,
            true,
            true,
            true,
            new ArrayList<>()
        );
    }
}
