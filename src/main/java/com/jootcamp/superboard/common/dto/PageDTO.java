package com.jootcamp.superboard.common.dto;

import java.util.List;

public interface PageDTO<T> {
    PageMetadata getPageMetadata();

    List<T> getData();

}
