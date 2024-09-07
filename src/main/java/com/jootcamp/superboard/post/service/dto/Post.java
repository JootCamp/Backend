package com.jootcamp.superboard.post.service.dto;

import com.jootcamp.superboard.post.repository.entity.PostEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {
    private String title;
    private String content;
    private long boardId;
    private Long id;
    private Long userId;
    private LocalDateTime time;

    public static Post from(PostEntity post) {
        return Post.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .boardId(post.getBoardId())
                .id(post.getId())
                .userId(post.getCreatedBy())
                .time(post.getModifiedAt())
                .build();
    }
}
