package com.jootcamp.superboard.common.dto;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageResponse<T> {

    private T data;
    private PageMetadata metadata;

    public PageResponse<T> from(PageDTO<T> pageDTO) {
        return PageResponse.<T>builder()
                .data(pageDTO.getData())
                .metadata(pageDTO.getPageMetaData())
                .build();
    }

}
