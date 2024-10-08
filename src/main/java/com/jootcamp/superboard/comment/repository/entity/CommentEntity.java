package com.jootcamp.superboard.comment.repository.entity;

import com.jootcamp.superboard.common.entity.BaseWithDeletedEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "comment")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentEntity extends BaseWithDeletedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private long userId;
    @Column(nullable = false)
    private long postId;
    @Column(nullable = false)
    private String writer;

    @Builder
    public CommentEntity(String content, long postId, long userId, String writer){
        this.content = content;
        this.postId = postId;
        this.userId = userId;
        this.writer = writer;
    }

    public void updateContent(String content){
        this.content = content;
    }
}
