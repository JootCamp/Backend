package com.jootcamp.superboard.user.repository.exception;

import com.jootcamp.superboard.common.exception.BadRequestException;

public class AlreadyExistEmailException extends BadRequestException {
    public AlreadyExistEmailException(String email){
        super("이미 존재하는 이메일입니다 "+ email);
    }
}
