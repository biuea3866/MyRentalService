package com.microservices.postservice.repository;

import com.microservices.postservice.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<ImageEntity, Long> {
    Iterable<ImageEntity> findByPostId(String postId);
}
