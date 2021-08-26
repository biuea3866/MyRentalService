package com.microservices.postservice.service;

import com.microservices.postservice.dto.CommentDto;
import com.microservices.postservice.entity.CommentEntity;
import com.microservices.postservice.repository.CommentRepository;
import com.microservices.postservice.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Transactional
    public Long writeComment(CommentDto commentDto) {
        CommentEntity commentEntity = CommentEntity.builder()
                                                   .writer(commentDto.getWriter())
                                                   .comment(commentDto.getComment())
                                                   .createdAt(DateUtil.dateNow())
                                                   .build();

        return commentRepository.save(commentEntity).getId();
    }

    @Transactional
    public String deleteComment(Long commentId) {
        CommentEntity commentEntity = commentRepository.findByCommentId(commentId);

        return "Successfully delete this comment";
    }
}
