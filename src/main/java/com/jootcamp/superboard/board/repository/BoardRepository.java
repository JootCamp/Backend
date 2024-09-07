package com.jootcamp.superboard.board.repository;

import com.jootcamp.superboard.board.repository.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

    Optional<BoardEntity> findByIdAndIsDeletedIsFalse(Long boardId);
    List<BoardEntity> findAllByIsDeletedIsFalse();

    boolean existsByIdAndIsDeletedIsFalse(Long boardId);
}
