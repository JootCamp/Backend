package com.jootcamp.superboard.post.service;

import com.jootcamp.superboard.post.repository.PostRepository;
import com.jootcamp.superboard.post.repository.entity.PostEntity;
import com.jootcamp.superboard.post.service.dto.Post;
import com.jootcamp.superboard.post.service.dto.UpsertPost;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public Post create(UpsertPost upsertPost) {
        PostEntity post = postRepository.save(upsertPost.toEntity());
        return Post.from(post);
    }
}
