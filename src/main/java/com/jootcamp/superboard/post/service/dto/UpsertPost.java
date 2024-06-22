package com.jootcamp.superboard.post.service.dto;

import com.jootcamp.superboard.post.repository.entity.PostEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpsertPost {
    private String title;
    private String content;
    private Long userId;

    public PostEntity toEntity() {
        return PostEntity.builder()
                .title(title)
                .content(content)
                .userId(userId)
                .build();
    }
}
