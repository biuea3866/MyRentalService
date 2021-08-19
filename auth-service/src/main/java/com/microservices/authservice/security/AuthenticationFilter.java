package com.microservices.authservice.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.authservice.vo.RequestLogin;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
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
        // Todo: 성공적으로 로그인을 했을 때 어떤 로직을 처리할지..
    }
}
