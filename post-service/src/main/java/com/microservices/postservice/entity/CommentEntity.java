package com.microservices.postservice.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name="comments")
@NoArgsConstructor
public class CommentEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="comment_id")
    Long id;

    @Column(nullable = false)
    String writer;

    @Column(nullable = false)
    String comment;

    @Column(nullable = false)
    String createdAt;

    @ManyToOne
    @JoinColumn(name="post_id")
    private PostEntity post;

    @Builder
    public CommentEntity(
        String writer,
        String comment,
        String createdAt,
        PostEntity post
    ) {
        this.writer = writer;
        this.comment = comment;
        this.createdAt = createdAt;
        this.post = post;
    }

    public void setPost(PostEntity post) {
        this.post = post;

        if(!post.getComments().contains(this)) {
            post.getComments().add(this);
        }
    }
}
