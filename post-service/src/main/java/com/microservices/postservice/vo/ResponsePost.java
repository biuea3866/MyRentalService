package com.microservices.postservice.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponsePost {
    private String postId;
    private String userId;
    private String postType;
    private Long rentalPrice;
    private String title;
    private String content;
    private String startDate;
    private String endDate;
    private int isLike;
    private int isDislike;
    private int viewCnt;
    private int delYn;
    private String createdAt;
    private String writer;
    private String status;
    private List<String> images;

    @Builder
    public ResponsePost(
        String postId,
        String userId,
        String postType,
        Long rentalPrice,
        String title,
        String content,
        String startDate,
        String endDate,
        int isLike,
        int isDislike,
        int viewCnt,
        int delYn,
        String createdAt,
        String writer,
        String status,
        List<String> images
    ) {
        this.postId = postId;
        this.userId = userId;
        this.postType = postType;
        this.rentalPrice = rentalPrice;
        this.title = title;
        this.content = content;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isLike = isLike;
        this.isDislike = isDislike;
        this.viewCnt = viewCnt;
        this.delYn = delYn;
        this.createdAt = createdAt;
        this.writer = writer;
        this.status = status;
        this.images = images;
    }
}
