package com.jootcamp.superboard.comment.repository.exception;

import com.jootcamp.superboard.common.exception.NotFoundException;
import org.aspectj.bridge.ICommand;

public class CommentNotFoundException extends NotFoundException {
    public CommentNotFoundException(long commentId){
        super("해당 댓글을 찾을 수 없습니다");
    }
}
