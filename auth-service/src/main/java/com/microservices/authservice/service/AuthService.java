package com.microservices.authservice.service;

import com.microservices.authservice.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthService extends UserDetailsService {
    UserDto registerUser(UserDto userDto);
}
