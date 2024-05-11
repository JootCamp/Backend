package com.jootcamp.superboard.user.service.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class User {
    private String email;
    private String password;
}
