package com.jootcamp.superboard.common.exception;

import com.jootcamp.superboard.common.exception.code.ErrorCode;
import com.jootcamp.superboard.common.exception.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> notFoundHandler(NotFoundException e) {
        ErrorCode errorCode = e.getErrorCode();
        String msg = e.getMessage();
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
}
