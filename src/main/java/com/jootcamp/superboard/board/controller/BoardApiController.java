package com.jootcamp.superboard.board.controller;

import com.jootcamp.superboard.board.controller.dto.BoardResponse;
import com.jootcamp.superboard.board.service.dto.Board;
import com.jootcamp.superboard.board.controller.dto.UpsertBoardRequest;
import com.jootcamp.superboard.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.jootcamp.superboard.common.constants.UserConstant.USER_ID;

@RequiredArgsConstructor
@RestController
public class BoardApiController {
    private final BoardService boardService;

    @PostMapping("/boards")
    public ResponseEntity<Long> createBoard(@RequestBody UpsertBoardRequest request) {
        Board savedBoard = boardService.create(request.toUpsertBoard(USER_ID));
        return ResponseEntity.status(HttpStatus.OK).body(savedBoard.getId());
    }

    @DeleteMapping("/boards/{boardId}")
    public ResponseEntity<Void> deleteBoard(@PathVariable long boardId) {
        boardService.delete(USER_ID, boardId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/boards/{boardId}")
    public ResponseEntity<Long> updateBoard(@PathVariable long boardId, @RequestBody UpsertBoardRequest request) {
        Board board = boardService.update(request.toUpsertBoard(USER_ID), boardId);
        return ResponseEntity.status(HttpStatus.OK).body(board.getId());
    }

    @GetMapping("/boards/{boardId}")
    public ResponseEntity<BoardResponse> findBoard(@PathVariable long boardId) {
        Board board = boardService.findById(boardId);
        return ResponseEntity.status(HttpStatus.OK).body(BoardResponse.from(board));
    }

    @GetMapping("/boards")
    public ResponseEntity<List<BoardResponse>> findAllBoards() {
        List<BoardResponse> boardResponses = boardService.findAll()
                .stream()
                .map(BoardResponse::from)
                .toList();

        return ResponseEntity.ok().body(boardResponses);
    }


}
