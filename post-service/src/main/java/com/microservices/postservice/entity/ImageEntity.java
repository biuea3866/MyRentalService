package com.microservices.postservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name="images")
public class ImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="image_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="post_id")
    @JsonBackReference
    private PostEntity post;

    @Column(nullable = false)
    private String orgFilename;

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false, updatable = false)
    private String filePath;

    private Long fileSize;

    @Builder
    public ImageEntity(
        Long id,
        PostEntity post,
        String orgFilename,
        String filePath,
        String fileName,
        Long fileSize
    ) {
        this.id = id;
        this.post = post;
        this.orgFilename = orgFilename;
        this.filePath = filePath;
        this.fileName = fileName;
        this.fileSize = fileSize;
    }

    public void setPost(PostEntity post) {
        this.post = post;

        if(!post.getImages().contains(this)) {
            post.getImages().add(this);
        }
    }
}
