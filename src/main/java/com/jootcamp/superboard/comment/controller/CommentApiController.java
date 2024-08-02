package com.jootcamp.superboard.comment.controller;

import com.jootcamp.superboard.comment.controller.dto.CommentResponse;
import com.jootcamp.superboard.comment.controller.dto.UpsertCommentRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @PostMapping("/posts/{postId}/comments")
    @Operation(summary = "댓글 작성", description = "댓글 작성 API")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "댓글 작성 성공시, 200 응답"),
                    @ApiResponse(responseCode = "400", description = "댓글 내용이 없다면 작성 실패, 400 응답")
            }
    )
    public ResponseEntity<Long> postComment(@RequestBody UpsertCommentRequest request, @PathVariable("postId") long postId) {
        return ResponseEntity.ok().body(1L);
    }

    // 게시글 댓글 전체 조회
    @GetMapping("/posts/{postId}/comments")
    @Operation(summary = "댓글 조회", description = "해당 게시글에 대한 댓글 조회")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "댓글 조회 성공시, 200 응답"),
                    @ApiResponse(responseCode = "404", description = "해당 게시글이 존재하지 않으면, 404 응답")
            }
    )
    public ResponseEntity<List<CommentResponse>> getComments(@RequestBody UpsertCommentRequest request, @PathVariable("postId") long postId) {

        return ResponseEntity.ok().body(List.of(new CommentResponse(1L, "test", "tester1s", LocalDateTime.now())));
    }

    // 댓글 수정
    @PutMapping("/posts/{postId}/comments/{commentId}")
    @Operation(summary = "댓글 수정", description = "commentId에 해당하는 특정 댓글 수정")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "댓글 수정 성공시, 200 응답"),
                    @ApiResponse(responseCode = "404", description = "해당 게시글이 존재하지 않으면, 404 응답"),
                    @ApiResponse(responseCode = "400", description = "해당 게시글이 존재하지 않으면, 400 응답")
            }
    )
    public ResponseEntity<Void> updateComment(@RequestBody UpsertCommentRequest request, @PathVariable("commentId") long commentId) {

        return ResponseEntity.ok().build();
    }

    // 댓글 삭제
    @DeleteMapping("posts/{postId}/comments/{commentId}")
    @Operation(summary = "댓글 삭제", description = "commentId에 해당하는 댓글 삭제")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "댓글 삭제 성공시, 200 응답"),
                    @ApiResponse(responseCode = "404", description = "해당 댓글이 존재하지 않으면, 404 응답")
            }
    )
    public ResponseEntity<List<CommentResponse>> deleteComment(@RequestBody UpsertCommentRequest request, @PathVariable("commentId") long commentId) {

        return ResponseEntity.ok().build();
    }
}
