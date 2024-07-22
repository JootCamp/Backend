package com.jootcamp.superboard.post.service.dto;

import com.jootcamp.superboard.common.dto.PageDTO;
import com.jootcamp.superboard.common.dto.PageMetadata;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Getter
public class PostPage<T> extends PageDTO<T> {

    public PostPage(T data, PageMetadata metadata){
        super(data, metadata);
    }

    public static <T> PostPage<T> of(T data, PageMetadata metadata){
        return new PostPage<>(data, metadata);
    }
}
