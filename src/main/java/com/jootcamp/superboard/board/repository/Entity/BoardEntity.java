package com.jootcamp.superboard.board.repository.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "board")
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private long id;

    @Column( nullable = false)
    private String title;


    private String description;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;

    private long createdBy;

    private boolean deleted;

    private Long modifiedBy;

    private Long deletedBy;

    @Builder
    public BoardEntity(String title, String description, long userId) {
        this.title = title;
        this.description = description;
        this.createdBy = userId;
        this.deleted = false;
    }

    public void delete(long userId) {
        this.deletedBy = userId;
        this.deleted = true;
    }

    public void update(String title, String description, long userId){
        this.title = title;
        this.description = description;
        this.modifiedBy = userId;
    }

}
