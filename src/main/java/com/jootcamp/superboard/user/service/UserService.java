package com.jootcamp.superboard.user.service;

import com.jootcamp.superboard.common.PasswordEncoder;
import com.jootcamp.superboard.user.repository.UserRepository;
import com.jootcamp.superboard.user.repository.entity.UserEntity;
import com.jootcamp.superboard.user.repository.exception.AlreadyExistEmailException;
import com.jootcamp.superboard.user.repository.exception.UserNotFoundException;
import com.jootcamp.superboard.user.service.dto.UpsertUser;
import com.jootcamp.superboard.user.service.dto.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

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
    public boolean login(User user, HttpServletRequest httpServletRequest) throws Exception {
        UserEntity userEntity = userRepository.findByEmailAndIsDeletedIsFalse(user.getEmail())
                .orElseThrow(() -> new UserNotFoundException(user.getEmail()));

        String encodingPass = passwordEncoder.encrypt(user.getPassword());

        if (user.getPassword().equals(userEntity.getPassword())) {
            // 로그인 성공 => 세션 생성

            // 세션을 생성하기 전에 기존의 세션 파기
            httpServletRequest.getSession().invalidate();
            HttpSession session = httpServletRequest.getSession(true);  // Session이 없으면 생성
            // 세션에 userId를 넣어줌
            session.setAttribute("userId", user.getEmail());
            session.setMaxInactiveInterval(1800); // Session이 30분동안 유지

            return true;
        } else return false;
    }

    //로그아웃

    //유저 정보 수정

    //유저 삭제
}
