package com.jootcamp.superboard.common.dto;

import lombok.*;

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
}
