package com.jootcamp.superboard.user.repository.entity;

import com.jootcamp.superboard.common.baseEntity.BaseWithDeletedEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "user")
@NoArgsConstructor
public class UserEntity extends BaseWithDeletedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column (nullable = false, unique = true)
    private String email;
    private String password;
    private String nickname;
    private String role;

    @Builder
    public UserEntity(String email, String password, String nickname) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.setDeleted(false);
    }
}
