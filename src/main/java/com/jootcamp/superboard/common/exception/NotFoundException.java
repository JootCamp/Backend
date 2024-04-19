package com.jootcamp.superboard.common.exception;

import com.jootcamp.superboard.common.exception.code.ErrorCode;
import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException{
    private ErrorCode errorCode;

    public NotFoundException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
