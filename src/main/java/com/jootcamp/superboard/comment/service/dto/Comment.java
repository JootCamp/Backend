package com.jootcamp.superboard.comment.service.dto;

import com.jootcamp.superboard.comment.repository.entity.CommentEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {
    private long commentId;
    private String writer;
    private LocalDateTime createdAt;

    public static Comment from(CommentEntity entity, String writer){
        return Comment.builder()
                .commentId(entity.getId())
                .writer(writer)
                .createdAt(entity.getModifiedAt())
                .build();
    }
}
