package com.microservices.authservice.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.authservice.dto.UserDto;
import com.microservices.authservice.service.AuthService;
import com.microservices.authservice.vo.RequestLogin;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

@Slf4j
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthService authService;
    private Environment env;

    public AuthenticationFilter(
        AuthenticationManager authenticationManager,
        AuthService authService,
        Environment env
    ) {
        super.setAuthenticationManager(authenticationManager);

        this.authService = authService;
        this.env = env;
    }

    @Override
    public Authentication attemptAuthentication(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws AuthenticationException {
        try {
            // http요청 중 body에 있는 내용을 RequestLogin.class로 매핑하겠다는 의
            RequestLogin creds = new ObjectMapper().readValue(
                request.getInputStream(),
                RequestLogin.class
            );

            // UsernamePasswordAuthenticationToken을 만들어 getAuthenticationManager().authenticate()
            // 에 넣어주면 이 Token값을 이용해 아이디와 패스워드를 비교해줌
            return getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(
                    creds.getEmail(),
                    creds.getPassword(),
                    new ArrayList<>()
                )
            );
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain chain,
        Authentication authResult
    ) throws IOException, ServletException {
        // 로그인시 사용자의 이메일 값
        String email = ((User)authResult.getPrincipal()).getUsername();
        UserDto userDto = authService.getUserDetailsByEmail(email);
        String token = Jwts.builder()
                           .setSubject(userDto.getUserId())
                           .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(env.getProperty("token.exp_time"))))
                           .signWith(SignatureAlgorithm.HS512, env.getProperty("token.secret"))
                           .compact();

        response.addHeader("token", token);
        response.addHeader("userId", userDto.getUserId());
    }
}
