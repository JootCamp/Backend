package com.jootcamp.superboard.post.service.dto;

import com.jootcamp.superboard.common.dto.PageDTO;
import com.jootcamp.superboard.common.dto.PageMetadata;
import lombok.NoArgsConstructor;

import java.util.List;


@NoArgsConstructor
public class PostPage<Post> implements PageDTO<Post> {
    private List<Post> posts;
    private PageMetadata pageMetadata;

    public PostPage (List<Post> posts, PageMetadata pageMetadata) {
        this.posts = posts;
        this.pageMetadata = pageMetadata;
    }

    @Override
    public PageMetadata getPageMetadata() {
        return pageMetadata;
    }

    @Override
    public List<Post> getData(){
        return posts;
    }
}
