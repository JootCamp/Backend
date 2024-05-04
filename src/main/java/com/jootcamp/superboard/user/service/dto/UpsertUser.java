package com.jootcamp.superboard.user.service.dto;

import com.jootcamp.superboard.user.repository.entity.UserEntity;
import lombok.Getter;

@Getter
public class UpsertUser {
    private String email;
    private String password;
    private String nickname;

    public UserEntity toEntity() {
        return UserEntity.builder()
                .email(email)
                .password(password)
                .nickname(nickname)
                .build();
    }
}
