package com.jootcamp.superboard.comment.service.dto;

import com.jootcamp.superboard.comment.repository.entity.CommentEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class UpsertComment {
    private long postId;
    private long userId;
    private String content;
    private String writer;

    public CommentEntity toEntity(){
        return CommentEntity.builder()
                .content(content)
                .postId(postId)
                .userId(userId)
                .writer(writer)
                .build();
    }
}
