package com.jootcamp.superboard.comment.controller;

import com.jootcamp.superboard.comment.controller.dto.CommentResponse;
import com.jootcamp.superboard.comment.controller.dto.UpsertCommentRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Comment API", description = "댓글 서비스 API 명세서")
public class CommentApiController {

    // 댓글 입력
    @PostMapping("/boards/{boardId}/posts/{postId}/comments")
    @Operation(summary = "댓글 작성", description = "댓글 작성 API")

    public ResponseEntity<Long> postComment(@RequestBody UpsertCommentRequest request, @PathVariable("postId") long postId) {
        return ResponseEntity.ok().body(1L);
    }

    // 게시글 댓글 전체 조회
    @GetMapping("/boards/{boardId}/posts/{postId}/comments")
    @Operation(summary = "댓글 조회", description = "해당 게시글에 대한 댓글 조회")

    public ResponseEntity<List<CommentResponse>> getComments(@PathVariable("postId") long postId) {

        return ResponseEntity.ok().body(List.of(new CommentResponse(1L, "test", "tester1s", LocalDateTime.now())));
    }

    // 댓글 수정
    @PutMapping("/boards/{boardId}/posts/{postId}/comments/{commentId}")
    @Operation(summary = "댓글 수정", description = "commentId에 해당하는 특정 댓글 수정")

    public ResponseEntity<Void> updateComment(@RequestBody UpsertCommentRequest request, @PathVariable("commentId") long commentId) {

        return ResponseEntity.ok().build();
    }

    // 댓글 삭제
    @DeleteMapping("/boards/{boardId}/posts/{postId}/comments/{commentId}")
    @Operation(summary = "댓글 삭제", description = "commentId에 해당하는 댓글 삭제")

    public ResponseEntity<List<CommentResponse>> deleteComment(@PathVariable("commentId") long commentId) {

        return ResponseEntity.ok().build();
    }
}
