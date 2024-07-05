package com.jootcamp.superboard.user.repository.exception;

import com.jootcamp.superboard.common.exception.NotFoundException;

public class UserNotFoundException extends NotFoundException {
    public UserNotFoundException (String email) {
        super("해당하는 이메일을 찾을 수 없습니다. "+ email);
    }
    public UserNotFoundException () {
        super("해당하는 이메일을 찾을 수 없습니다. ");
    }
}
