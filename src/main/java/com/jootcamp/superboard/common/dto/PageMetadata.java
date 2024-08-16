package com.jootcamp.superboard.common.dto;

import lombok.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageMetadata {
    private int currentPage;
    private int size;
    private long totalCount;
    private int totalPageCount;

    public static <T> PageMetadata of(Pageable pageable, Page<T> pages){
        return PageMetadata.builder()  // 타입 명시적 처리
                .currentPage(pageable.getPageNumber())
                .size(pages.getSize())
                .totalCount(pages.getTotalElements())
                .totalPageCount(pages.getTotalPages())
                .build();
    }
}
