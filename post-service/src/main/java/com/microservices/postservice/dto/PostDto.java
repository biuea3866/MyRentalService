package com.microservices.postservice.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class PostDto {
    private String postId;
    private String userId;
    private String postType;
    private Long rentalPrice;
    private String title;
    private String content;
    private List<String> date;
    private int isLike;
    private int isDislike;
    private int viewCnt;
    private int delYn;
    private String createdAt;
    private String writer;
    private String status;
    private List<MultipartFile> images;

    @Builder
    public PostDto(
        String postId,
        String userId,
        String postType,
        Long rentalPrice,
        String title,
        String content,
        List<String> date,
        int isLike,
        int isDislike,
        int viewCnt,
        int delYn,
        String createdAt,
        String writer,
        String status,
        List<MultipartFile> images
    ) {
        this.postId = postId;
        this.userId = userId;
        this.postType = postType;
        this.rentalPrice = rentalPrice;
        this.title = title;
        this.content = content;
        this.date = date;
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
