package com.jootcamp.superboard.comment.controller.dto;

import com.jootcamp.superboard.comment.service.dto.UpsertComment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpsertCommentRequest {
    private String content;

    public UpsertComment toUpsertComment(long postId, long userId, String writer){
        return UpsertComment.builder()
                .content(content)
                .postId(postId)
                .userId(userId)
                .writer(writer)
                .build();
    }
}
