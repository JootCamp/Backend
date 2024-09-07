package com.jootcamp.superboard.post.dto;

import com.jootcamp.superboard.post.service.dto.UpsertPost;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpsertPostRequest {
    private String title;
    private String content;

    public UpsertPost toUpsertPost(long userId, long boardId) {
        return UpsertPost.builder()
                .title(title)
                .content(content)
                .userId(userId)
                .boardId(boardId)
                .build();
    }
}
