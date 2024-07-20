package com.jootcamp.superboard.post.service;

import com.jootcamp.superboard.common.dto.PageMetaData;
import com.jootcamp.superboard.common.dto.ResponsePage;
import com.jootcamp.superboard.post.repository.PostRepository;
import com.jootcamp.superboard.post.repository.entity.PostEntity;
import com.jootcamp.superboard.post.repository.execption.PostNotFoundException;
import com.jootcamp.superboard.post.service.dto.Post;
import com.jootcamp.superboard.post.service.dto.UpsertPost;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public Post create(UpsertPost upsertPost) {
        PostEntity post = postRepository.save(upsertPost.toEntity());
        return Post.from(post);
    }

    public ResponsePage<List<Post>> findAll(Pageable pageable) {
        Page<PostEntity> posts = postRepository.findAllByIsDeletedIsFalse(pageable);

        List<Post> postList = posts.getContent().stream().map(Post::from).toList();
        PageMetaData metaData = PageMetaData.builder()
                .currentPage(pageable.getPageNumber())
                .size(posts.getSize())
                .totalCount(posts.getTotalElements())
                .totalPageCount(posts.getTotalPages())
                .build();

        return new ResponsePage<>(postList, metaData);
    }

    public Post findById(long postId) {
        PostEntity post = postRepository.findByIdAndIsDeletedIsFalse(postId)
                .orElseThrow(()->new PostNotFoundException(postId));

        return Post.from(post);
    }

    @Transactional
    public void delete(long userId, long postId) {
        PostEntity post = postRepository.findByIdAndIsDeletedIsFalse(postId)
                .orElseThrow(()-> new PostNotFoundException(postId));

        post.delete(userId);
    }


    @Transactional
    public Post update(UpsertPost upsertPost, long postId) {
        PostEntity post = postRepository.findByIdAndIsDeletedIsFalse(postId)
                .orElseThrow(()->new PostNotFoundException(postId));

        post.update(upsertPost.getTitle(), upsertPost.getContent(), upsertPost.getUserId());

        return Post.from(post);
    }
}
