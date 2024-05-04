package com.jootcamp.superboard.user.repository.exception;

public class AlreadyExistEmailException extends RuntimeException{
    public AlreadyExistEmailException(String email){
        super("이미 존재하는 이메일입니다 "+ email);
    }
}
