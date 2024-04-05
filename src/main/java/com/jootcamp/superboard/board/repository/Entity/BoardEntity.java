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
    @Column(name = "id", updatable = false)
    private long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "deleted")
    private boolean deleted;

    @Column(name = "modified_by")
    private String modifiedBy;

    @Column(name = "deleted_by")
    private String deletedBy;

    @Builder
    public BoardEntity(String title, String description, String userName) {
        this.title = title;
        this.description = description;
        this.createdBy = userName;
        this.deleted = false;
    }

    public void delete(String userName) {
        this.deletedBy = userName;
        this.deleted = true;
    }

    public void update(String title, String description, String userName){
        this.title = title;
        this.description = description;
        this.modifiedBy = userName;
    }

}
