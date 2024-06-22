package com.jootcamp.superboard.post.controller;

import com.jootcamp.superboard.post.dto.PostResponse;
import com.jootcamp.superboard.post.dto.UpsertPostRequest;
import com.jootcamp.superboard.post.service.PostService;
import com.jootcamp.superboard.post.service.dto.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.jootcamp.superboard.common.constants.UserConstant.USER_ID;


@RequiredArgsConstructor
@Controller
public class PostApiController {
    private final PostService postService;

    @PostMapping("/posts")
    public ResponseEntity<PostResponse> createPost(@RequestBody UpsertPostRequest postRequest) {
        Post savaPost = postService.create(postRequest.toUpsertPost(USER_ID));
        return ResponseEntity.ok().body(PostResponse.from(savaPost));

    }
}
