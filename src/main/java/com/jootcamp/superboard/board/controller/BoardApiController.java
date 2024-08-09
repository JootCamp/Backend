package com.jootcamp.superboard.board.controller;

import com.jootcamp.superboard.board.controller.dto.BoardResponse;
import com.jootcamp.superboard.board.service.dto.Board;
import com.jootcamp.superboard.board.controller.dto.UpsertBoardRequest;
import com.jootcamp.superboard.board.service.BoardService;
import com.jootcamp.superboard.common.UserInfo;
import com.jootcamp.superboard.user.controller.dto.AuthUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Tag(name = "Board API", description = "게시판 서비스 API 명세서")
public class BoardApiController {
    private final BoardService boardService;

    @PostMapping("/boards")
    @Operation(summary = "게시판 생성", description = "게시판 생성 API")
    public ResponseEntity<Long> createBoard(@RequestBody UpsertBoardRequest request, @UserInfo AuthUser authUser) {
        Board savedBoard = boardService.create(request.toUpsertBoard(authUser.getUserId()));
        return ResponseEntity.status(HttpStatus.OK).body(savedBoard.getId());
    }

    @DeleteMapping("/boards/{boardId}")
    @Operation(summary = "게시판 삭제", description = "게시판 삭제 API")
    public ResponseEntity<Void> deleteBoard(@PathVariable("boardId") long boardId, @UserInfo AuthUser authUser) {
        boardService.delete(authUser.getUserId(), boardId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/boards/{boardId}")
    @Operation(summary = "게시판 수정", description = "게시판 수정 API")
    public ResponseEntity<Long> updateBoard(@PathVariable("boardId") long boardId, @RequestBody UpsertBoardRequest request, @UserInfo AuthUser authUser) {
        Board board = boardService.update(request.toUpsertBoard(authUser.getUserId()), boardId);
        return ResponseEntity.status(HttpStatus.OK).body(board.getId());
    }

    @GetMapping("/boards/{boardId}")
    @Operation(summary = "게시판 조회", description = "게시판 단건 조회 API")
    public ResponseEntity<BoardResponse> findBoard(@PathVariable("boardId") long boardId) {
        Board board = boardService.findById(boardId);
        return ResponseEntity.status(HttpStatus.OK).body(BoardResponse.from(board));
    }

    @GetMapping("/boards")
    @Operation(summary = "게시판 조회", description = "게시판 전제 목록 조회 API")
    public ResponseEntity<List<BoardResponse>> findAllBoards() {
        List<BoardResponse> boardResponses = boardService.findAll()
                .stream()
                .map(BoardResponse::from)
                .toList();

        return ResponseEntity.ok().body(boardResponses);
    }


}
