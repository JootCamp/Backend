package com.jootcamp.superboard.user.controller;

import com.jootcamp.superboard.common.PasswordEncoder;
import com.jootcamp.superboard.user.controller.dto.UpsertUserRequest;
import com.jootcamp.superboard.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserApiController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    //회원가입
    @PostMapping("/signup")
    public ResponseEntity<Boolean> signUp(@RequestBody UpsertUserRequest userRequest) throws Exception {
        String encodingPass = passwordEncoder.encrypt(userRequest.getPassword());
        Boolean result = userService.signUp(userRequest.toUpsertUser(encodingPass));
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    
}
