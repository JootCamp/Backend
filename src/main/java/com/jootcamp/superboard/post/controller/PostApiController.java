package com.jootcamp.superboard.post.controller;

import com.jootcamp.superboard.common.UserInfo;
import com.jootcamp.superboard.common.dto.IdResponse;
import com.jootcamp.superboard.common.dto.PageResponse;
import com.jootcamp.superboard.post.dto.PostResponse;
import com.jootcamp.superboard.post.dto.UpsertPostRequest;
import com.jootcamp.superboard.post.service.PostService;
import com.jootcamp.superboard.post.service.dto.Post;
import com.jootcamp.superboard.user.controller.dto.AuthUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@Controller
@Tag(name = "Post API", description = "게시글 서비스 API 명세서")
public class PostApiController {
    private final PostService postService;

    @PostMapping("/boards/{boardId}/posts")
    @Operation(summary = "게시글 생성", description = "게시글 생성 API")
    public ResponseEntity<IdResponse> createPost(@RequestBody UpsertPostRequest postRequest,
                                                 @PathVariable("boardId") long boardId,
                                                 @UserInfo AuthUser authUser) {
        long postId = postService.create(postRequest.toUpsertPost(authUser.getUserId(), boardId));
        return ResponseEntity.ok().body(new IdResponse(postId));
    }

    @GetMapping("/boards/{boardId}/posts")
    @Operation(summary = "게시글 조회", description = "특정 게시판의 게시글 전체 조회 API")
    public ResponseEntity<PageResponse<Post>> findAllPost(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                                                          @PathVariable("boardId") long boardId) {
        PageResponse<Post> posts = PageResponse.from(postService.findAll(pageable, boardId));
        return ResponseEntity.ok().body(posts);
    }

    @GetMapping("/boards/{boardId}/posts/{postId}")
    @Operation(summary = "게시글 조회", description = "게시글 단건 조회 API")
    public ResponseEntity<PostResponse> findPost(@PathVariable("postId") long postId) {
        Post post = postService.findById(postId);
        return ResponseEntity.ok().body(PostResponse.from(post));
    }

    @DeleteMapping("/boards/{boardId}/posts/{postId}")
    @Operation(summary = "게시글 삭제", description = "게시글 삭제 API")
    public ResponseEntity<Void> deletePost(@PathVariable("postId") long postId,
                                           @UserInfo AuthUser authUser) {
        postService.delete(authUser.getUserId(), postId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/boards/{boardId}/posts/{postId}")
    @Operation(summary = "게시글 수정", description = "게시글 수정 API")
    public ResponseEntity<IdResponse> updateBoard(@PathVariable("postId") long postId,
                                                  @PathVariable("boardId") long boardId,
                                                  @RequestBody UpsertPostRequest upsertPostRequest,
                                                  @UserInfo AuthUser authUser) {
        postService.update(upsertPostRequest.toUpsertPost(authUser.getUserId(), boardId), postId);
        return ResponseEntity.ok().body(new IdResponse(postId));
    }
}
