package com.jootcamp.superboard.board.repository.entity;

import com.jootcamp.superboard.common.entity.BaseWithDeletedEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "board")
public class BoardEntity extends BaseWithDeletedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private long id;

    @Column( nullable = false)
    private String title;

    private String description;

    @Builder
    public BoardEntity(String title, String description, long userId) {
        this.title = title;
        this.description = description;
        this.setCreatedBy(userId);
        this.setDeleted(false);
    }

    public void delete(long userId) {
        this.setDeletedBy(userId);
        this.setDeleted(true);
    }

    public void update(String title, String description, long userId){
        this.title = title;
        this.description = description;
        this.setModifiedBy(userId);
    }

}
