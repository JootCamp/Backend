package com.jootcamp.superboard.comment.repository;

import com.jootcamp.superboard.comment.repository.entity.CommentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    Page<CommentEntity> findAllByPostIdAndIsDeletedIsFalse(long postId, Pageable pageable);

    Optional<CommentEntity> findByIdAndIsDeletedIsFalse(long id);


}
