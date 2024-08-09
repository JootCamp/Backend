package com.jootcamp.superboard.comment.service.dto;

import com.jootcamp.superboard.common.dto.PageDTO;
import com.jootcamp.superboard.common.dto.PageMetadata;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class CommentPage<Comment> implements PageDTO<Comment> {
    private List<Comment> comments;
    private PageMetadata pageMetadata;

    public CommentPage (List<Comment> comments, PageMetadata pageMetadata){
        this.comments = comments;
        this.pageMetadata = pageMetadata;
    }

    @Override
    public PageMetadata getPageMetadata() {
        return pageMetadata;
    }

    @Override
    public List<Comment> getData() {
        return comments;
    }
}
