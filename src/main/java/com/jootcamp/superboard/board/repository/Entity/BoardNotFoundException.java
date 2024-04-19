package com.jootcamp.superboard.board.repository.Entity;

import com.jootcamp.superboard.common.exception.NotFoundException;
import com.jootcamp.superboard.common.exception.code.ErrorCode;
import lombok.Getter;

import static com.jootcamp.superboard.common.exception.code.ErrorCode.NOT_FOUND_BOARD_ERROR;

public class BoardNotFoundException extends NotFoundException {


    public BoardNotFoundException(String message, ErrorCode errorCode){
        super(message, errorCode);
    }
}
