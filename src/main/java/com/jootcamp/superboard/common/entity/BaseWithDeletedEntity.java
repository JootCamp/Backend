package com.jootcamp.superboard.common.entity;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseWithDeletedEntity extends BaseEntity {
    private boolean isDeleted;
    private Long deletedBy;

}
