package com.jootcamp.superboard.user.controller.dto;

import com.jootcamp.superboard.user.service.dto.User;
import lombok.Getter;

@Getter
public class LoginRequest {
    private String email;
    private String password;

    public User toUser() {
        return User.builder()
                .email(email)
                .password(password)
                .build();
    }
}
