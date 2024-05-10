package com.jootcamp.superboard.user.controller;

import com.jootcamp.superboard.user.controller.dto.LoginRequest;
import com.jootcamp.superboard.user.controller.dto.UpsertUserRequest;
import com.jootcamp.superboard.user.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
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

    //회원가입
    @PostMapping("/signup")
    public ResponseEntity<Boolean> signup(@RequestBody UpsertUserRequest userRequest) throws Exception {

        Boolean result = userService.signup(userRequest.toUpsertUser());
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody LoginRequest loginRequest, HttpServletRequest httpServletRequest) throws Exception {
        

        Boolean result = userService.login(loginRequest.toUser(), httpServletRequest);
        if(result) return ResponseEntity.status(HttpStatus.OK).build();

        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }

}
