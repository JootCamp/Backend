package com.jootcamp.superboard.user.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthUser {
    private long userId;
    private String userEmail;
    private String nickname;
}
