package com.jootcamp.superboard.user.repository.entity;

import com.jootcamp.superboard.common.baseEntity.BaseWithDeletedEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "user")
public class UserEntity extends BaseWithDeletedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String email;
    private String password;
    private String nickname;
    private String role;
}
