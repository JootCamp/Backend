package com.jootcamp.superboard.comment.controller;

import com.jootcamp.superboard.comment.controller.dto.CommentResponse;
import com.jootcamp.superboard.comment.controller.dto.UpsertCommentRequest;
import com.jootcamp.superboard.comment.service.CommentService;
import com.jootcamp.superboard.comment.service.dto.Comment;
import com.jootcamp.superboard.common.UserInfo;
import com.jootcamp.superboard.common.dto.IdResponse;
import com.jootcamp.superboard.common.dto.PageResponse;
import com.jootcamp.superboard.user.controller.dto.AuthUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Comment API", description = "댓글 서비스 API 명세서")
public class CommentApiController {

    private final CommentService commentService;

    // 댓글 입력
    @PostMapping("/boards/{boardId}/posts/{postId}/comments")
    @Operation(summary = "댓글 작성", description = "댓글 작성 API")

    public ResponseEntity<IdResponse> postComment(@RequestBody UpsertCommentRequest request,
                                            @PathVariable("boardId") long boardId,
                                            @PathVariable("postId") long postId,
                                            @UserInfo AuthUser authUser) {
        long commentId = commentService.addComments(request.toUpsertComment(postId, authUser.getUserId(), authUser.getNickname()));
        return ResponseEntity.ok().body(new IdResponse(commentId));
    }

    // 게시글 댓글 전체 조회
    @GetMapping("/boards/{boardId}/posts/{postId}/comments")
    @Operation(summary = "댓글 조회", description = "해당 게시글에 대한 댓글 조회")

    public ResponseEntity<PageResponse<Comment>> getComments(@PathVariable("boardId") long boardId,
                                                             @PathVariable("postId") long postId,
                                                             Pageable pageable) {
        PageResponse<Comment> comments = PageResponse.from(commentService.getComments(postId, pageable));
        return ResponseEntity.ok().body(comments);
    }

    // 댓글 수정
    @PutMapping("/boards/{boardId}/posts/{postId}/comments/{commentId}")
    @Operation(summary = "댓글 수정", description = "commentId에 해당하는 특정 댓글 수정")

    public ResponseEntity<IdResponse> updateComment(@RequestBody UpsertCommentRequest request,
                                              @PathVariable("postId") long postId,
                                              @PathVariable("commentId") long commentId,
                                              @UserInfo AuthUser authUser) {

        commentService.updateComment(request.toUpsertComment(postId, authUser.getUserId(), authUser.getNickname()),commentId);
        return ResponseEntity.ok().body(new IdResponse(commentId));
    }

    // 댓글 삭제
    @DeleteMapping("/boards/{boardId}/posts/{postId}/comments/{commentId}")
    @Operation(summary = "댓글 삭제", description = "commentId에 해당하는 댓글 삭제")

    public ResponseEntity<List<CommentResponse>> deleteComment(@PathVariable("boardId") long boardId,
                                                               @PathVariable("postId") long postId,
                                                               @PathVariable("commentId") long commentId,
                                                               @UserInfo AuthUser user) {
        commentService.deleteComment(commentId, postId, user.getUserId());
        return ResponseEntity.ok().build();
    }
}
