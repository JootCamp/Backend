package com.jootcamp.superboard.common.exception.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse {
   private String errorMessage;           // 에러 메시지

    @Builder
    public ErrorResponse(String msg) {
        this.errorMessage = msg;
    }
}
