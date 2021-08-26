package com.microservices.postservice.repository;

import com.microservices.postservice.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
    PostEntity findByPostId(String postId);

    Iterable<PostEntity> findAllByStatus(String status);

    Iterable<PostEntity> findAllByUserId(String userId);

    PostEntity findPostByPostId(String postId);
}
