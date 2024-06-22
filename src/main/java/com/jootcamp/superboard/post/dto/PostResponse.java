package com.jootcamp.superboard.post.dto;

import com.jootcamp.superboard.post.service.dto.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class PostResponse {
    private String title;
    private String content;
    private Long id;
    private Long userId;
    private LocalDateTime time;

    public static PostResponse from(Post post) {
        return PostResponse.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .id(post.getId())
                .userId(post.getUserId())
                .time(post.getTime())
                .build();
    }
}
