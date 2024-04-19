package com.jootcamp.superboard.common.exception;

public class ForbiddenException extends RuntimeException{
    public ForbiddenException(String mes) {
        super(mes);
    }
}
