package com.jootcamp.superboard.common.dto;

import java.util.List;

public interface PageDto<T> {
    PageMetadata getPageMetadata();

    List<T> getData();

}
