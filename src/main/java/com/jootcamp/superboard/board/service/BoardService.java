package com.jootcamp.superboard.board.service;

import com.jootcamp.superboard.board.repository.Entity.BoardEntity;
import com.jootcamp.superboard.board.repository.Entity.EntityNotFoundException;
import com.jootcamp.superboard.board.service.dto.UpsertBoard;
import com.jootcamp.superboard.board.service.dto.Board;
import com.jootcamp.superboard.board.repository.BoardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;

    public Board create(UpsertBoard upsertBoard) {
        BoardEntity board = boardRepository.save(upsertBoard.toEntity());
        return Board.from(board);
    }

    public List<Board> findAll() {
        List<BoardEntity> boards = boardRepository.findAllByDeletedIsFalse();
        return boards.stream()
                .map(Board::from)
                .toList();
    }

    public Board findById(long boardId) {

        BoardEntity board = boardRepository.findByIdAndDeletedIsFalse(boardId)
                .orElseThrow(()->new EntityNotFoundException("not found Board : "+ boardId));

        return Board.from(board);
    }

    @Transactional
    public void delete(long userId, long boardId) {
        BoardEntity board = boardRepository.findByIdAndDeletedIsFalse(boardId)
                .orElseThrow(()-> new IllegalArgumentException("not found Board : "+ boardId));

        // soft delete 이렇게 하는게 맞아?
        if(board!=null) board.delete(userId);
    }

    @Transactional
    public Board update(UpsertBoard updateBoard, long boardId) {
        BoardEntity board = boardRepository.findByIdAndDeletedIsFalse(boardId)
                .orElseThrow(()-> new IllegalArgumentException("not found Board : "+ boardId));

        return Board.builder()
                .id(board.getId())
                .title(board.getTitle())
                .description(board.getDescription())
        .build();
    }

}
