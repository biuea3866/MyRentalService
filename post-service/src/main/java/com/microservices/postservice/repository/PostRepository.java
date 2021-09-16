package com.microservices.postservice.repository;

import com.microservices.postservice.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.List;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
    Iterable<PostEntity> findAllByStatus(String status);

    Iterable<PostEntity> findAllByUserId(String userId);

    PostEntity findPostById(Long id);

    @Query(
        value="SELECT * " +
              "FROM posts " +
              "LIKE '%' + :keyword + '%' ",
        nativeQuery = true
    )
    Iterable<PostEntity> findByKeywordLike(String keyword);

    @Query(
        value="SELECT * " +
              "FROM posts p " +
              "WHERE p.category = :category " +
              "NOT IN(:exceptList.get(0), :exceptList().get(1))",
        nativeQuery = true
    )
    Iterable<PostEntity> findAllByCategory(
        String category,
        ArrayList<String> exceptList
    );

    Iterable<PostEntity> findAllByStatusNotIn(List<String> exceptList);
}
