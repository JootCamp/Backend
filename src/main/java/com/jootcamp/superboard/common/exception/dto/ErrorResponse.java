package com.jootcamp.superboard.common.exception.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.FieldError;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse {
//    private int status;                 // 에러 상태 코드
//    private String divisionCode;        // 에러 구분 코드
   private String resultMsg;           // 에러 메시지
//    private List<FieldError> errors;    // 상세 에러 메시지
//    private String reason;              // 에러 이유

    @Builder
    public ErrorResponse(String msg) {
        this.resultMsg = msg;
    }
}
