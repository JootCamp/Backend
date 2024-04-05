package com.jootcamp.superboard.board.controller;

import com.jootcamp.superboard.board.controller.dto.BoardResponse;
import com.jootcamp.superboard.board.service.dto.Board;
import com.jootcamp.superboard.board.controller.dto.UpsertBoardRequest;
import com.jootcamp.superboard.board.service.dto.DeleteBoard;
import com.jootcamp.superboard.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.jootcamp.superboard.common.constants.UserConstant.USER_NAME;

@RequiredArgsConstructor
@RestController
public class BoardApiController {
    private final BoardService boardService;

    @PostMapping("/boards")
    public ResponseEntity<Void> CreateBoard(@RequestBody UpsertBoardRequest request) {
        Board savedBoard = boardService.create(request.toCreateBoard(USER_NAME));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/boards/{id}")
    public ResponseEntity<Void> deleteBoard(@PathVariable long id) {
        boardService.delete(DeleteBoard.builder().id(id).userName(USER_NAME).build());

        return ResponseEntity.ok().build();
    }

    @PutMapping("/boards/{id}")
    public ResponseEntity<BoardResponse> updateBoard(@PathVariable long id, @RequestBody UpsertBoardRequest request) {
        Board board = boardService.update(request.toUpdateBoard(id, USER_NAME));
        return ResponseEntity.status(HttpStatus.CREATED).body(BoardResponse.from(board));
    }

    @GetMapping("/boards/{id}")
    public ResponseEntity<BoardResponse> findBoard(@PathVariable long id) {
        Board board = boardService.findById(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(BoardResponse.from(board));
    }

    @GetMapping("/boards")
    public ResponseEntity<List<BoardResponse>> findAllBoards() {
        List<BoardResponse> boardResponses = boardService.findAll()
                .stream()
                .map(BoardResponse::from)
                .toList();

        return ResponseEntity.ok()
                .body(boardResponses);
    }


}
