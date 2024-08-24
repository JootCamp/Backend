package com.jootcamp.superboard.common.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class PageDto<T> {
    private List<T> contents;
    private PageMetadata pageMetadata;

    @Builder
    public PageDto (List<T> contents, PageMetadata pageMetadata) {
        this.pageMetadata = pageMetadata;
        this.contents = contents;
    }

    public static <T> PageDto<T> of(List<T> contents, PageMetadata pageMetadata) {
        return PageDto.<T>builder()
                .contents(contents)
                .pageMetadata(pageMetadata)
                .build();
    }
}
