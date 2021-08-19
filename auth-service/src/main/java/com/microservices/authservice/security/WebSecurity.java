package com.microservices.authservice.security;

import com.microservices.authservice.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
    private AuthService authService;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public WebSecurity(
        AuthService authService,
        BCryptPasswordEncoder passwordEncoder
    ) {
        this.authService = authService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests().antMatchers("/**").hasIpAddress("10.0.15.41")
            .and().addFilter(getAuthenticationFilter());
    }

    private AuthenticationFilter getAuthenticationFilter() throws Exception {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter();

        authenticationFilter.setAuthenticationManager(authenticationManager());

        return authenticationFilter;
    }

    // 인증에 관한 작업
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 로그인 처리에 있어 사용자 정보를 검색해옴
        // dp.pwd(encrypted) == request.pwd(encode)
        auth.userDetailsService(authService)
            .passwordEncoder(passwordEncoder);
    }
}
