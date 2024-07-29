package com.jootcamp.superboard.comment.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpsertCommentRequest {
    private String content;
}
