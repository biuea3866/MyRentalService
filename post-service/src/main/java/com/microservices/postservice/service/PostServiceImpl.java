package com.microservices.postservice.service;

import com.microservices.postservice.dto.PostDto;
import com.microservices.postservice.entity.CommentEntity;
import com.microservices.postservice.entity.ImageEntity;
import com.microservices.postservice.entity.PostEntity;
import com.microservices.postservice.repository.ImageRepository;
import com.microservices.postservice.repository.PostRepository;
import com.microservices.postservice.util.DateUtil;
import com.microservices.postservice.util.FileUploader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class PostServiceImpl implements PostService {
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
    public Long write(PostDto postDto) throws Exception {
        log.info("Post Service's Service Layer :: Call write Method!");

        PostEntity postEntity = PostEntity.builder()
                                          .postId(UUID.randomUUID().toString())
                                          .postType(postDto.getPostType())
                                          .rentalPrice(postDto.getRentalPrice())
                                          .title(postDto.getTitle())
                                          .content(postDto.getContent())
                                          .startDate(postDto.getStartDate())
                                          .endDate(postDto.getEndDate())
                                          .writer(postDto.getWriter())
                                          .userId(postDto.getUserId())
                                          .createdAt(DateUtil.dateNow())
                                          .status("CREATE_POST")
                                          .build();
//        List<ImageEntity> images = FileUploader.parseFileInfo(
//            postDto.getMultipartFiles(),
//            postEntity
//        );
//
//        if(!images.isEmpty()) {
//            for(ImageEntity image: images) {
//                postEntity.addImage(imageRepository.save(image));
//            }
//        }

        return postRepository.save(postEntity).getId();
    }

    @Transactional
    @Override
    public PostDto readPostByPostId(String postId) {
        log.info("Post Service's Service Layer :: Call write Method!");

        PostEntity postEntity = postRepository.findByPostId(postId);
//        List<ImageEntity> images = new ArrayList<>();
        List<CommentEntity> comments = new ArrayList<>();

//        postEntity.getImages().forEach(i -> {
//            images.add(i);
//        });

        postEntity.getComments().forEach(i -> {
            comments.add(i);
        });

        return PostDto.builder()
                      .postId(postEntity.getPostId())
                      .userId(postEntity.getUserId())
                      .postType(postEntity.getPostType())
                      .rentalPrice(postEntity.getRentalPrice())
                      .title(postEntity.getTitle())
                      .content(postEntity.getContent())
                      .startDate(postEntity.getStartDate())
                      .endDate(postEntity.getEndDate())
                      .createdAt(postEntity.getCreatedAt())
                      .writer(postEntity.getWriter())
//                      .images(images)
                      .comments(comments)
                      .build();
    }

    @Transactional
    @Override
    public List<PostDto> getAllPosts() {
        log.info("Post Service's Service Layer :: Call getAllPosts Method!");

        Iterable<PostEntity> posts = postRepository.findAll();
        List<PostDto> postList = new ArrayList<>();

        posts.forEach(v -> {
            postList.add(PostDto.builder()
                                .postId(v.getPostId())
                                .userId(v.getUserId())
                                .postType(v.getPostType())
                                .rentalPrice(v.getRentalPrice())
                                .title(v.getTitle())
                                .content(v.getContent())
                                .startDate(v.getStartDate())
                                .endDate(v.getEndDate())
                                .createdAt(v.getCreatedAt())
                                .writer(v.getWriter())
//                                .images(v.getImages())
                                .comments(v.getComments())
                                .build());
        });

        return postList;
    }

    @Transactional
    @Override
    public Iterable<PostDto> getAllPostsByStatus(String status) {
        log.info("Post Service's Service Layer :: Call getAllPostsByCreate Method!");

        Iterable<PostEntity> posts = postRepository.findAllByStatus(status);
        List<PostDto> postList = new ArrayList<>();

        posts.forEach(v -> {
            postList.add(PostDto.builder()
                    .postId(v.getPostId())
                    .userId(v.getUserId())
                    .postType(v.getPostType())
                    .rentalPrice(v.getRentalPrice())
                    .title(v.getTitle())
                    .content(v.getContent())
                    .startDate(v.getStartDate())
                    .endDate(v.getEndDate())
                    .createdAt(v.getCreatedAt())
                    .writer(v.getWriter())
    //                .images(v.getImages())
                    .comments(v.getComments())
                    .build());
        });

        return postList;
    }

    @Transactional
    @Override
    public List<PostDto> getPostsByUserId(String userId) {
        log.info("Post Service's Service Layer :: Call getPostsByUserId Method!");

        Iterable<PostEntity> posts = postRepository.findAllByUserId(userId);
        List<PostDto> postList = new ArrayList<>();

        posts.forEach(v -> {
            postList.add(PostDto.builder()
                                .postId(v.getPostId())
                                .userId(v.getUserId())
                                .postType(v.getPostType())
                                .rentalPrice(v.getRentalPrice())
                                .title(v.getTitle())
                                .content(v.getContent())
                                .startDate(v.getStartDate())
                                .endDate(v.getEndDate())
                                .createdAt(v.getCreatedAt())
                                .writer(v.getWriter())
//                                .images(v.getImages())
                                .comments(v.getComments())
                                .build());
        });

        return postList;
    }

    @Transactional
    @Override
    public PostDto deletePost(String postId) {
        log.info("Post Service's Service Layer :: Call deletePost Method!");

        PostEntity postEntity = postRepository.findPostByPostId(postId);

        postEntity.setStatus("DELETE_POST");

        postRepository.save(postEntity);

        return PostDto.builder()
                      .postId(postEntity.getPostId())
                      .status(postEntity.getStatus())
                      .build();
    }
}
