package com.jootcamp.superboard.post.service;

import com.jootcamp.superboard.common.dto.PageDto;
import com.jootcamp.superboard.common.dto.PageMetadata;
import com.jootcamp.superboard.common.exception.BadRequestException;
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

    public long create(UpsertPost upsertPost) {
        PostEntity post = postRepository.save(upsertPost.toEntity());
        return post.getId();
    }

    public PageDto<Post> findAll(Pageable pageable, long boardId) {
        Page<PostEntity> posts = postRepository.findAllByIsDeletedIsFalse(pageable);

        List<Post> postList = posts.getContent().stream().map(Post::from).toList();
        PageMetadata metadata = PageMetadata.of(pageable, posts);

        return PageDto.of(postList, metadata);
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
    public void update(UpsertPost upsertPost, long postId) {
        PostEntity post = postRepository.findByIdAndIsDeletedIsFalse(postId)
                .orElseThrow(()->new PostNotFoundException(postId));

        post.update(upsertPost.getTitle(), upsertPost.getContent(), upsertPost.getUserId(), upsertPost.getBoardId());
    }

    // 게시글과 게시판 검증
    public boolean existsPost(long boardId, long postId){
        return postRepository.existsByBoardIdAndIdAndIsDeletedIsFalse(boardId, postId);
    }
}
