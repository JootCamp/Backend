package com.jootcamp.superboard.user.repository.exception;

import com.jootcamp.superboard.common.exception.NotFoundException;

public class NotFoundUserException extends NotFoundException {
    public NotFoundUserException() {
        super("해당하는 회원을 찾을 수 없습니다. ");
    }
}
