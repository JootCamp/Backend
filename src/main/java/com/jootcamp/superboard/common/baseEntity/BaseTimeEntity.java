package com.jootcamp.superboard.common.baseEntity;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass // BaseEntity를 상속받은 자식 클래스에게, 부모 클래스의 매핑 정보를 모두 제공해주는 어노테이션입니다. 해당 어노테이션은 Entity로 인식되지 않으며, 데이터베이스에 테이블이 생성되지 않습니다.
@EntityListeners(AuditingEntityListener.class) // 자동으로 값 매핑 기능 추가
public abstract class BaseTimeEntity {

    @CreatedDate
    private LocalDateTime createdAt;

    private long createdBy;

    @LastModifiedDate
    private LocalDateTime modifiedAt;

    private Long modifiedBy;



}
