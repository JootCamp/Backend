package com.jootcamp.superboard.post.repository.entity;

import com.jootcamp.superboard.common.entity.BaseWithDeletedEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "post")
public class PostEntity extends BaseWithDeletedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private long id;

    @Column(nullable = false)
    private String title;

    private String content;

    private long boardId;

    @Builder
    public PostEntity(String title, String content, long userId, long boardId){
        this.title = title;
        this.content = content;
        this.setCreatedBy(userId);
        this.setDeleted(false);
        this.boardId = boardId;
    }

    public void delete(long userId) {
        this.setDeletedBy(userId);
        this.setDeleted(true);
    }

    public void update(String title, String content, long userId, long boardId) {
        this.title = title;
        this.content = content;
        this.setModifiedBy(userId);
        this.boardId = boardId;
    }

}
