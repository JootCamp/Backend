package com.jootcamp.superboard.post.repository;

import com.jootcamp.superboard.post.repository.entity.PostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
    Optional<PostEntity> findByIdAndIsDeletedIsFalse(Long postId);
    Page<PostEntity> findAllByIsDeletedIsFalse(Pageable pageable);
    Optional<PostEntity> existsByBoardIdAndIdAndIsDeletedIsFalse(Long boardId, Long postId);
}
