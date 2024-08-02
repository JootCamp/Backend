package com.jootcamp.superboard.comment.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponse {
    private long id;
    private String content;
    private String writer;
    private LocalDateTime createdBy;
}
