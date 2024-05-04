package com.jootcamp.superboard.user.service;

import com.jootcamp.superboard.user.repository.UserRepository;
import com.jootcamp.superboard.user.repository.entity.UserEntity;
import com.jootcamp.superboard.user.repository.exception.AlreadyExistEmailException;
import com.jootcamp.superboard.user.service.dto.UpsertUser;
import com.jootcamp.superboard.user.service.dto.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    UserRepository userRepository;

    //유저 생성
    public boolean signUp(UpsertUser userData) {
        // email 중복 체크
        UserEntity user = userRepository.findByEmailAndIsDeleteFalse(userData.getEmail())
                .orElseThrow( ()->  new AlreadyExistEmailException(userData.getEmail()));

        user = userRepository.save(userData.toEntity());
        return true;
    }

    //유저 전체 조회

    //특정 유저 조회

    //유저 정보 수정

    //유저 삭제
}
