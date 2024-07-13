package com.jootcamp.superboard.user.repository.exception;

import com.jootcamp.superboard.common.exception.NotFoundException;

public class UserEmailNotFoundException extends NotFoundException {
    public UserEmailNotFoundException(String email) {
        super("해당하는 이메일을 찾을 수 없습니다. "+ email);
    }
    public UserEmailNotFoundException() {
        super("해당하는 이메일을 찾을 수 없습니다. ");
    }
}
