package com.microservices.postservice.vo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
public class RequestWrite {
    @NotNull(message="postType cannot be null")
    private String postType;

    private String category;

    @NotNull(message="title cannot be null")
    @Size(min=10, message="title cannot be less than 10 characters")
    private String title;

    @NotNull(message="content cannot be null")
    @Size(min=10, message="content cannot be less than 10 characters")
    private String content;

    private Long rentalPrice;

    private List<String> date;

    @NotNull(message="writer cannot be null")
    private String writer;

    @NotNull(message="userId cannot be null")
    private String userId;

    List<MultipartFile> images;
}
