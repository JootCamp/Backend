package com.jootcamp.superboard.user.controller.dto;

import com.jootcamp.superboard.user.service.dto.UpsertUser;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpsertUserRequest {
    private String email;
    private String password;
    private String nickname;
    private String name;

    public UpsertUser toUpsertUser() {
        return UpsertUser.builder()
                .email(email)
                .password(password)
                .name(name)
                .nickname(nickname)
                .build();
    }
}
