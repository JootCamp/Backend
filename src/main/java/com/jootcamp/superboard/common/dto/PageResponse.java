package com.jootcamp.superboard.common.dto;

import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageResponse<T> {

    private List<T> data;
    private PageMetadata metadata;

    public static<T> PageResponse<T> from(PageDto<T> pageDTO) {
        return PageResponse.<T>builder()
                .data(pageDTO.getData())
                .metadata(pageDTO.getPageMetadata())
                .build();
    }

}
