package com.microservices.postservice.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="posts")
@NoArgsConstructor
public class PostEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(
        name="post_id",
        nullable = false,
        unique = true
    )
    private String postId;

    @Column(nullable = false, unique = true)
    private String userId;

    @Column(nullable = false)
    private String postType;

    @Column(nullable = true)
    private Long rentalPrice;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = true)
    private String startDate;

    @Column(nullable = true)
    private String endDate;

    private String createdAt;

    private String writer;

    private String status;

    @OneToMany(
        mappedBy = "post",
        cascade = { CascadeType.PERSIST, CascadeType.REMOVE },
        orphanRemoval = true
    )
    private List<ImageEntity> images = new ArrayList<>();

    @OneToMany(
        mappedBy = "post",
        cascade = { CascadeType.PERSIST, CascadeType.REMOVE },
        orphanRemoval = true
    )
    private List<CommentEntity> comments = new ArrayList<>();

    @Builder
    public PostEntity(
        String postId,
        String userId,
        String postType,
        Long rentalPrice,
        String title,
        String content,
        String startDate,
        String endDate,
        String createdAt,
        String writer,
        String status
    ) {
        this.postId = postId;
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
    }

    public void addImage(ImageEntity image) {
        this.images.add(image);

        if(image.getPost() != this) {
            image.setPost(this);
        }
    }

    public void addComment(CommentEntity comment) {
        this.comments.add(comment);

        if(comment.getPost() != this) {
            comment.setPost(this);
        }
    }
}
