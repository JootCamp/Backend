package com.jootcamp.superboard.user.repository.entity;

import com.jootcamp.superboard.common.entity.BaseWithDeletedEntity;
import com.jootcamp.superboard.user.service.dto.UpsertUser;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "member")
@NoArgsConstructor
public class UserEntity extends BaseWithDeletedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column (nullable = false, unique = true)
    private String email;
    private String password;
    private String nickname;
    private String name;
    private String role;

    @Builder
    public UserEntity(UpsertUser user) {
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.nickname = user.getNickname();
        this.name = user.getName();
        this.setDeleted(false);
        this.role = "USER";
    }
}
