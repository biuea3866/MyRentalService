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
    public PostDto write(PostDto postDto) throws Exception {
        log.info("Post Service's Service Layer :: Call write Method!");

        PostEntity postEntity = PostEntity.builder()
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

        postRepository.save(postEntity);

        return PostDto.builder()
                      .id(postEntity.getId())
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
                      .build();
    }

    @Transactional
    @Override
    public PostDto readPostById(Long id) {
        log.info("Post Service's Service Layer :: Call readPostById Method!");

        PostEntity postEntity = postRepository.findPostById(id);
//        List<ImageEntity> images = new ArrayList<>();
        List<CommentEntity> comments = new ArrayList<>();

//        postEntity.getImages().forEach(i -> {
//            images.add(i);
//        });

        postEntity.getComments().forEach(i -> {
            comments.add(CommentEntity.builder()
                                      .id(i.getId())
                                      .comment(i.getComment())
                                      .writer(i.getWriter())
                                      .createdAt(i.getCreatedAt())
                                      .build());
        });

        return PostDto.builder()
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
                      .status(postEntity.getStatus())
                      .build();
    }

    @Transactional
    @Override
    public List<PostDto> getAllPosts() {
        log.info("Post Service's Service Layer :: Call getAllPosts Method!");

        Iterable<PostEntity> posts = postRepository.findAll();
        List<PostDto> postList = new ArrayList<>();

        posts.forEach(v -> {
            List<CommentEntity> comments = new ArrayList<>();

            v.getComments().forEach(i -> {
                comments.add(CommentEntity.builder()
                                          .id(i.getId())
                                          .comment(i.getComment())
                                          .writer(i.getWriter())
                                          .createdAt(i.getCreatedAt())
                                          .build());
            });

            postList.add(PostDto.builder()
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
                                .comments(comments)
                                .status(v.getStatus())
                                .build());
        });

        return postList;
    }

    @Transactional
    @Override
    public Iterable<PostDto> getAllPostsByStatus(String status) {
        log.info("Post Service's Service Layer :: Call getAllPostsByStatus Method!");

        Iterable<PostEntity> posts = postRepository.findAllByStatus(status);
        List<PostDto> postList = new ArrayList<>();

        posts.forEach(v -> {
            List<CommentEntity> comments = new ArrayList<>();

            v.getComments().forEach(i -> {
                comments.add(CommentEntity.builder()
                                          .id(i.getId())
                                          .comment(i.getComment())
                                          .writer(i.getWriter())
                                          .createdAt(i.getCreatedAt())
                                          .build());
            });

            postList.add(PostDto.builder()
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
                                .comments(comments)
                                .status(v.getStatus())
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
            List<CommentEntity> comments = new ArrayList<>();

            v.getComments().forEach(i -> {
                comments.add(CommentEntity.builder()
                                          .id(i.getId())
                                          .comment(i.getComment())
                                          .writer(i.getWriter())
                                          .createdAt(i.getCreatedAt())
                                          .build());
            });

            postList.add(PostDto.builder()
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
                                .comments(comments)
                                .status(v.getStatus())
                                .build());
        });

        return postList;
    }

    @Transactional
    @Override
    public PostDto deletePost(Long id) {
        log.info("Post Service's Service Layer :: Call deletePost Method!");

        PostEntity postEntity = postRepository.findPostById(id);

        postEntity.setStatus("DELETE_POST");

        postRepository.save(postEntity);

        return PostDto.builder()
                      .id(postEntity.getId())
                      .status(postEntity.getStatus())
                      .build();
    }
}
