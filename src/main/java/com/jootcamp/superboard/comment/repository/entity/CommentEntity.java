package com.jootcamp.superboard.comment.repository.entity;

import com.jootcamp.superboard.common.entity.BaseWithDeletedEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "comment")
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
}
