package com.microservices.postservice.service;

import com.microservices.postservice.dto.PostDto;
import com.microservices.postservice.entity.ImageEntity;
import com.microservices.postservice.entity.PostEntity;
import com.microservices.postservice.util.FileUploader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class PostServiceImpl implements PostService{
    private PostRepository postRepository;
    private ImageRepository imageRepository;

    @Autowired
    public PostServiceImpl(
        PostRepository postRepository,
        ImageRepository imageRepository
    ) {
        this.postRepository = postRepository;
        this.imageRepository = imageRepository;
    }


    @Transactional
    @Override
    public PostDto write(PostDto postDto) throws Exception {
        log.info("Post Service's Service Layer :: Call write Method!");

        PostEntity postEntity = PostEntity.builder()
                                          .postId(UUID.randomUUID().toString())
                                          .postType(postDto.getPostType())
                                          .rentalPrice(postDto.getRentalPrice())
                                          .title(postDto.getTitle())
                                          .content(postDto.getContent())
                                          .startDate(postDto.getDate().get(0))
                                          .endDate(postDto.getDate().get(1))
                                          .isLike(0)
                                          .isDislike(0)
                                          .viewCnt(0)
                                          .delYn(0)
                                          .writer(postDto.getWriter())
                                          .build();
        List<ImageEntity> images = FileUploader.parseFileInfo(
            postDto.getImages(),
            postEntity
        );

        postRepository.save(postEntity);


    }

    @Transactional
    @Override
    public PostDto readPostByPostId(String postId) {
        return null;
    }

    @Transactional
    @Override
    public Iterable<PostDto> getAllPosts() {
        return null;
    }

    @Transactional
    @Override
    public Iterable<PostDto> getPostsByUserId(String userId) {
        return null;
    }

    @Transactional
    @Override
    public PostDto deletePost(String postId) {
        return null;
    }
}
