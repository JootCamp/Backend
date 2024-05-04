package com.jootcamp.superboard.user.controller;

import com.jootcamp.superboard.common.PasswordEncoder;
import com.jootcamp.superboard.user.controller.dto.LoginRequest;
import com.jootcamp.superboard.user.controller.dto.UpsertUserRequest;
import com.jootcamp.superboard.user.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
    public ResponseEntity<Boolean> signup(@RequestBody UpsertUserRequest userRequest) throws Exception {
        String encodingPass = passwordEncoder.encrypt(userRequest.getPassword());
        Boolean result = userService.signup(userRequest.toUpsertUser(encodingPass));
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody LoginRequest loginRequest, HttpServletRequest httpServletRequest) throws Exception {
        
        String encodingPass = passwordEncoder.encrypt(loginRequest.getPassword());
        Boolean result = userService.login(loginRequest.toUser(encodingPass));
        if(result) {
            // 로그인 성공 => 세션 생성

            // 세션을 생성하기 전에 기존의 세션 파기
            httpServletRequest.getSession().invalidate();
            HttpSession session = httpServletRequest.getSession(true);  // Session이 없으면 생성
            // 세션에 userId를 넣어줌
            session.setAttribute("userId", loginRequest.getEmail());
            session.setMaxInactiveInterval(1800); // Session이 30분동안 유지

            return ResponseEntity.status(HttpStatus.OK).build();
        }
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }

}
