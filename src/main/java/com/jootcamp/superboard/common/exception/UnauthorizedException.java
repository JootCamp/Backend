package com.jootcamp.superboard.common.exception;

public class UnauthorizedException extends RuntimeException{
    public UnauthorizedException (String msg) {
        super(msg);
    }
}
