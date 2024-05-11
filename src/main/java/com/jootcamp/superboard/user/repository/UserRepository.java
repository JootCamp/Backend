package com.jootcamp.superboard.user.repository;

import com.jootcamp.superboard.user.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmailAndIsDeletedIsFalse(String email);
    List<UserEntity> findAllByIsDeletedIsFalse();
}