package com.jootcamp.superboard.board.controller;

import com.jootcamp.superboard.board.controller.dto.BoardResponse;
import com.jootcamp.superboard.board.service.dto.Board;
import com.jootcamp.superboard.board.controller.dto.UpsertBoardRequest;
import com.jootcamp.superboard.board.service.BoardService;
import com.jootcamp.superboard.common.UserInfo;
import com.jootcamp.superboard.user.controller.dto.AuthUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BoardApiController {
    private final BoardService boardService;

    @PostMapping("/boards")
    public ResponseEntity<Long> createBoard(@RequestBody UpsertBoardRequest request, @UserInfo AuthUser authUser) {
        Board savedBoard = boardService.create(request.toUpsertBoard(authUser.getUserId()));
        return ResponseEntity.status(HttpStatus.OK).body(savedBoard.getId());
    }

    @DeleteMapping("/boards/{boardId}")
    public ResponseEntity<Void> deleteBoard(@PathVariable("boardId") long boardId, @UserInfo AuthUser authUser) {
        boardService.delete(authUser.getUserId(), boardId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/boards/{boardId}")
    public ResponseEntity<Long> updateBoard(@PathVariable("boardId") long boardId, @RequestBody UpsertBoardRequest request, @UserInfo AuthUser authUser) {
        Board board = boardService.update(request.toUpsertBoard(authUser.getUserId()), boardId);
        return ResponseEntity.status(HttpStatus.OK).body(board.getId());
    }

    @GetMapping("/boards/{boardId}")
    public ResponseEntity<BoardResponse> findBoard(@PathVariable("boardId") long boardId) {
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
