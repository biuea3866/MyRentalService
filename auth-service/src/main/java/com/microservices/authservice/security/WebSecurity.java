package com.microservices.authservice.security;

import com.microservices.authservice.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
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
    private Environment env;

    @Autowired
    public WebSecurity(
        AuthService authService,
        BCryptPasswordEncoder passwordEncoder,
        Environment env
    ) {
        this.authService = authService;
        this.passwordEncoder = passwordEncoder;
        this.env = env;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
            .antMatchers("/**")
            .access("hasIpAddress('10.0.0.0/8') or hasIpAddress('192.0.0.0/8') or hasIpAddress('172.0.0.0/8') or hasIpAddress('127.0.0.1')")
            .and()
            .addFilter(getAuthenticationFilter())
            .logout()
            .logoutUrl("/logout")
            .logoutSuccessUrl("/");
    }

    private AuthenticationFilter getAuthenticationFilter() throws Exception {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(
            authenticationManager(),
            authService,
            env
        );

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
