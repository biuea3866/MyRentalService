package com.microservices.postservice.repository;

import com.microservices.postservice.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
    Iterable<PostEntity> findAllByStatus(String status);

    Iterable<PostEntity> findAllByUserId(String userId);

    PostEntity findPostById(Long id);

    Iterable<PostEntity> findByKeywordLike(String s);

    Iterable<PostEntity> findAllByCategory(String category);

    Iterable<PostEntity> findAllByStatusNotIn(List<String> exceptList);
}
