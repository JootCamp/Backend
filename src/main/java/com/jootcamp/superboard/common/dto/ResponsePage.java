package com.jootcamp.superboard.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResponsePage<T> {

    private T data;
    private PageMetaData pageMetaData;
}
