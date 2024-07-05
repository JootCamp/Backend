package com.jootcamp.superboard.post.controller;

import com.jootcamp.superboard.common.UserInfo;
import com.jootcamp.superboard.post.dto.PostResponse;
import com.jootcamp.superboard.post.dto.UpsertPostRequest;
import com.jootcamp.superboard.post.service.PostService;
import com.jootcamp.superboard.post.service.dto.Post;
import com.jootcamp.superboard.user.controller.dto.AuthUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@Controller
public class PostApiController {
    private final PostService postService;

    @PostMapping("/posts")
    public ResponseEntity<PostResponse> createPost(@RequestBody UpsertPostRequest postRequest, @UserInfo AuthUser authUser) {
        Post savedPost = postService.create(postRequest.toUpsertPost(authUser.getUserId()));
        return ResponseEntity.ok().body(PostResponse.from(savedPost));
    }

    @GetMapping("/posts")
    public ResponseEntity<List<PostResponse>> findAllPost() {
        List<PostResponse> postResponses = postService.findAll()
                .stream()
                .map(PostResponse::from)
                .toList();

        return ResponseEntity.ok().body(postResponses);
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostResponse> findPost(@PathVariable("postId") long postId) {
        Post post = postService.findById(postId);
        return ResponseEntity.ok().body(PostResponse.from(post));
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable("postId") long postId, @UserInfo AuthUser authUser) {
        postService.delete(authUser.getUserId(), postId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostResponse> updateBoard(@PathVariable("postId") long postId, @RequestBody UpsertPostRequest upsertPostRequest, @UserInfo AuthUser authUser) {
        Post post = postService.update(upsertPostRequest.toUpsertPost(authUser.getUserId()), postId);
        return ResponseEntity.ok().body(PostResponse.from(post));
    }
}
