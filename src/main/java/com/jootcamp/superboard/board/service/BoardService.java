package com.jootcamp.superboard.board.service;

import com.jootcamp.superboard.board.repository.Entity.BoardEntity;
import com.jootcamp.superboard.board.service.dto.UpsertBoard;
import com.jootcamp.superboard.board.service.dto.DeleteBoard;
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

    public Board findById(long id) {

        BoardEntity board = boardRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("not found Board : "+ id));

        // 만약 삭제된 게시물이라면?
        if (board.isDeleted()) {
            throw new IllegalStateException("게시글이 삭제되었습니다. id = " + id);
        }

        return Board.from(board);
    }

    @Transactional
    public void delete(DeleteBoard deleteBoard) {
        BoardEntity board = boardRepository.findById(deleteBoard.getId())
                .orElseThrow(()-> new IllegalArgumentException("not found Board : "+ deleteBoard.getId()));
        // 만약 삭제된 게시물이라면?
        board.delete(deleteBoard.getUserName());
    }

    @Transactional
    public Board update(UpsertBoard updateBoard) {
        BoardEntity board = boardRepository.findById(updateBoard.getId())
                .orElseThrow(()-> new IllegalArgumentException("not found Board : "+ updateBoard.getId()));
        // 만약 삭제된 게시물이라면?
        board.update(updateBoard.getTitle(), updateBoard.getDescription(), updateBoard.getUserName());

        return Board.builder()
                .id(board.getId())
                .title(board.getTitle())
                .description(board.getDescription())
        .build();
    }

}
