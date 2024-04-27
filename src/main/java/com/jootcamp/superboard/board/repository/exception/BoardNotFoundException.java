package com.jootcamp.superboard.board.repository.exception;

import com.jootcamp.superboard.common.exception.NotFoundException;
import com.jootcamp.superboard.common.exception.code.ErrorCode;

public class BoardNotFoundException extends NotFoundException {


    public BoardNotFoundException(long boardId){
        super("해당 게시판을 찾을 수 없습니다: " + boardId);
    }
}
