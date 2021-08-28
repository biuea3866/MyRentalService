package com.microservices.postservice.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.microservices.postservice.entity.CommentEntity;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponsePost {
    private Long id;
    private String userId;
    private String postType;
    private Long rentalPrice;
    private String title;
    private String content;
    private String startDate;
    private String endDate;
    private String createdAt;
    private String writer;
    private String status;
//    private List<ImageEntity> images;
    private List<CommentEntity> comments;

    @Builder
    public ResponsePost(
        Long id,
        String userId,
        String postType,
        Long rentalPrice,
        String title,
        String content,
        String startDate,
        String endDate,
        String createdAt,
        String writer,
        String status,
//        List<ImageEntity> images,
        List<CommentEntity> comments
    ) {
        this.id = id;
        this.userId = userId;
        this.postType = postType;
        this.rentalPrice = rentalPrice;
        this.title = title;
        this.content = content;
        this.startDate = startDate;
        this.endDate = endDate;
        this.createdAt = createdAt;
        this.writer = writer;
        this.status = status;
//        this.images = images;
        this.comments = comments;
    }
}
