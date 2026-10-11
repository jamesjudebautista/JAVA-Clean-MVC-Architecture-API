package com.example.todoapi.todo_api.config;

import com.example.todoapi.todo_api.utils.Constant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws  Exception{
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(a ->a
                        .requestMatchers(Constant.PUBLIC_ENDPOINTS).permitAll()
                        .requestMatchers("/error").permitAll().
                        anyRequest().authenticated());
        return  http.build();
    }
}
