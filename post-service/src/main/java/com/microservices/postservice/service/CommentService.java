package com.microservices.postservice.service;

import com.microservices.postservice.dto.CommentDto;
import com.microservices.postservice.entity.CommentEntity;
import com.microservices.postservice.repository.CommentRepository;
import com.microservices.postservice.repository.PostRepository;
import com.microservices.postservice.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CommentService {
    private CommentRepository commentRepository;
    private PostRepository postRepository;

    @Autowired
    public CommentService(
        CommentRepository commentRepository,
        PostRepository postRepository
    ) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Transactional
    public Long writeComment(CommentDto commentDto) {
        CommentEntity commentEntity = CommentEntity.builder()
                                                   .writer(commentDto.getWriter())
                                                   .comment(commentDto.getComment())
                                                   .post(postRepository.findByPostId(commentDto.getPostId()))
                                                   .createdAt(DateUtil.dateNow())
                                                   .build();

        return commentRepository.save(commentEntity).getId();
    }

    @Transactional
    public String deleteComment(Long id) {
        CommentEntity commentEntity = commentRepository.getOne(id);

        commentRepository.delete(commentEntity);

        return "Successfully delete this comment";
    }
}
