package com.jootcamp.superboard.post.repository.entity;

import com.jootcamp.superboard.common.baseEntity.BaseWithDeletedEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table
public class PostEntity extends BaseWithDeletedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private long id;

    @Column(nullable = false)
    private String title;

    private String content;

    @Builder
    public PostEntity(String title, String content, long userId){
        this.title = title;
        this.content = content;
        this.setCreatedBy(userId);
        this.setDeleted(false);
    }

}
