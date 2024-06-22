package com.jootcamp.superboard.post.repository.execption;

import com.jootcamp.superboard.common.exception.NotFoundException;

public class PostNotFoundException extends NotFoundException {
    public PostNotFoundException(long postId) {
        super("해당 게시글을 찾을 수 없습니다. "+postId);
    }
}
