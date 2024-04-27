package com.jootcamp.superboard.common.baseEntity;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseWithDeletedEntity extends BaseTimeEntity {
    private boolean isDeleted;
    private Long deletedBy;

}
