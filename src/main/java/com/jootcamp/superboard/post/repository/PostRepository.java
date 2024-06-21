package com.jootcamp.superboard.post.repository;

import com.jootcamp.superboard.post.repository.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
}
