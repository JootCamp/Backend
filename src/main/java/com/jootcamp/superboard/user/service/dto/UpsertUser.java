package com.jootcamp.superboard.user.service.dto;

import com.jootcamp.superboard.user.repository.entity.UserEntity;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UpsertUser {
    private String email;
    private String password;
    private String nickname;
    private String name;

    public UserEntity toEntity() {
        return UserEntity.builder()
                .user(this)
                .build();
    }
}
