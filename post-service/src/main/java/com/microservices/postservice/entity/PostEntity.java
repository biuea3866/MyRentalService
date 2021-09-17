package com.microservices.postservice.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="post_id")
    private Long id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String postType;

    @Column(nullable = true)
    private String category;

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

    @Column(nullable = false)
    private String createdAt;

    @Column(nullable = false)
    private String writer;

    @Column(nullable = false)
    private String status;

    @OneToMany(
        fetch = FetchType.LAZY,
        mappedBy = "post",
        cascade = { CascadeType.PERSIST, CascadeType.REMOVE },
        orphanRemoval = true
    )
    @JsonManagedReference
    private List<ImageEntity> images = new ArrayList<>();

    @OneToMany(
        fetch = FetchType.LAZY,
        mappedBy = "post",
        cascade = { CascadeType.PERSIST, CascadeType.REMOVE },
        orphanRemoval = true
    )
    @JsonManagedReference
    private List<CommentEntity> comments = new ArrayList<>();

    @Builder
    public PostEntity(
        Long id,
        String userId,
        String postType,
        String category,
        Long rentalPrice,
        String title,
        String content,
        String startDate,
        String endDate,
        String createdAt,
        String writer,
        String status
    ) {
        this.id = id;
        this.userId = userId;
        this.postType = postType;
        this.category = category;
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
