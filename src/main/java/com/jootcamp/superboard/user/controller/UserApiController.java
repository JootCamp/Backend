package com.jootcamp.superboard.user.controller;

import com.jootcamp.superboard.user.controller.dto.LoginRequest;
import com.jootcamp.superboard.user.controller.dto.UpsertUserRequest;
import com.jootcamp.superboard.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "User API", description = "유저 서비스를 위한 API 명세서입니다.")
public class UserApiController {
    private final UserService userService;
    private final HttpServletResponse httpServletResponse;

    //회원가입
    @PostMapping("/signup")
    @Operation(summary = "회원 가입", description = "회원가입 API")
    public ResponseEntity<Boolean> signup(@RequestBody UpsertUserRequest userRequest) throws Exception {
        Boolean result = userService.signup(userRequest.toUpsertUser());
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody LoginRequest loginRequest,
                                      HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        boolean result = userService.login(loginRequest.toUser(), httpServletRequest, httpServletResponse);
        if (result) return ResponseEntity.status(HttpStatus.OK).build();

        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }

    @GetMapping("/isLogin")
    @Operation(summary = "로그인 확인", description = "회원이 로그인 되어있는지 확인 API")
    public ResponseEntity<String> isLogin(HttpServletRequest httpRequest) {
        HttpSession session = httpRequest.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("unknown");
        }
        return ResponseEntity.status(HttpStatus.OK).body(session.getAttribute("userId").toString());
    }

}
