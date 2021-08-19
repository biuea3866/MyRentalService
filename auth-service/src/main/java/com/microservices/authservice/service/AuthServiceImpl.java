package com.microservices.authservice.service;

import com.microservices.authservice.dto.UserDto;
import com.microservices.authservice.entity.UserEntity;
import com.microservices.authservice.repository.AuthRepository;
import com.microservices.authservice.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.UUID;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {
    private AuthRepository authRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public AuthServiceImpl(
        AuthRepository authRepository,
        BCryptPasswordEncoder passwordEncoder
    ) {
        this.authRepository = authRepository;
        this.passwordEncoder = passwordEncoder;
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
