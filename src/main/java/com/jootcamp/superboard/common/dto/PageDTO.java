package com.jootcamp.superboard.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PageDTO<T> {
    private T data;
    private PageMetadata pageMetaData;
}
