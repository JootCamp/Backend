package com.jootcamp.superboard.user.service;

import com.jootcamp.superboard.common.PasswordEncoder;
import com.jootcamp.superboard.jwt.JWTUtil;
import com.jootcamp.superboard.user.controller.dto.AuthUser;
import com.jootcamp.superboard.user.repository.UserRepository;
import com.jootcamp.superboard.user.repository.entity.UserEntity;
import com.jootcamp.superboard.user.repository.exception.AlreadyExistEmailException;
import com.jootcamp.superboard.user.repository.exception.UserEmailNotFoundException;
import com.jootcamp.superboard.user.service.dto.UpsertUser;
import com.jootcamp.superboard.user.service.dto.User;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtil jwtUtil;

    //유저 생성
    public boolean signup(UpsertUser userData) throws Exception {
        // email 중복 체크
        userRepository.findByEmailAndIsDeletedIsFalse(userData.getEmail())
                .ifPresent(data -> {throw new AlreadyExistEmailException(userData.getEmail());});

        String encodingPass = passwordEncoder.encrypt(userData.getPassword());

        userRepository.save(userData.toEntity(encodingPass));

        return true;
    }

    //로그인
    public boolean login(User user, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        UserEntity userEntity = userRepository.findByEmailAndIsDeletedIsFalse(user.getEmail())
                .orElseThrow(() -> new UserEmailNotFoundException(user.getEmail()));

        String encodingPass = passwordEncoder.encrypt(user.getPassword());

        if (encodingPass.equals(userEntity.getPassword())) {
            // 로그인 성공 => 세션 생성

            // 세션을 생성하기 전에 기존의 세션 파기
            httpServletRequest.getSession().invalidate();
            HttpSession session = httpServletRequest.getSession(true);  // Session이 없으면 생성
            // 세션에 userId를 넣어줌
            session.setAttribute("USER_INFO", AuthUser.builder()
                    .userId(userEntity.getId())
                    .userEmail(userEntity.getEmail())
                    .nickname(userEntity.getNickname())
                    .build()
            );
            session.setMaxInactiveInterval(1800); // Session이 30분동안 유지


            String token= jwtUtil.createJwt(user.getEmail());

            Cookie jwtCookie = new Cookie("JOOT_TOKEN", token);
            jwtCookie.setHttpOnly(true);  // JS로 쿠키에 접근할 수 없도록 설정 (보안 강화)
            jwtCookie.setSecure(true);    // HTTPS에서만 전송되도록 설정 (개발 환경에서는 false로 설정 가능)
            jwtCookie.setPath("/");       // 쿠키가 전송될 경로 설정
            jwtCookie.setMaxAge(60 * 60 * 24); // 쿠키의 만료 시간 설정 (1일)

            // 응답에 쿠키 추가
            httpServletResponse.addCookie(jwtCookie);


            return true;
        } else return false;
    }

    //로그아웃

    //유저 정보 수정

    //유저 삭제
}
