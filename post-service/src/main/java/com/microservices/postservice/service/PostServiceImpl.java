package com.microservices.postservice.service;

import com.microservices.postservice.dto.PostDto;
import com.microservices.postservice.entity.CommentEntity;
import com.microservices.postservice.entity.ImageEntity;
import com.microservices.postservice.entity.PostEntity;
import com.microservices.postservice.repository.ImageRepository;
import com.microservices.postservice.repository.PostRepository;
import com.microservices.postservice.states.PostStatus;
import com.microservices.postservice.util.DateUtil;
import com.microservices.postservice.util.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

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
                                          .category(postDto.getCategory())
                                          .rentalPrice(postDto.getRentalPrice())
                                          .title(postDto.getTitle())
                                          .content(postDto.getContent())
                                          .startDate(postDto.getStartDate())
                                          .endDate(postDto.getEndDate())
                                          .writer(postDto.getWriter())
                                          .userId(postDto.getUserId())
                                          .createdAt(DateUtil.dateNow())
                                          .status(postDto.getStatus())
                                          .build();
        List<ImageEntity> images = FileUtil.parseFileInfo(
            postDto.getMultipartFiles(),
            postEntity
        );

        postRepository.save(postEntity);

        if(!images.isEmpty()) {
            for (ImageEntity image : images) {
                postEntity.addImage(imageRepository.save(image));
            }
        }

        return PostDto.builder()
                      .id(postEntity.getId())
                      .userId(postEntity.getUserId())
                      .postType(postEntity.getPostType())
                      .category(postEntity.getCategory())
                      .rentalPrice(postEntity.getRentalPrice())
                      .title(postEntity.getTitle())
                      .content(postEntity.getContent())
                      .startDate(postEntity.getStartDate())
                      .endDate(postEntity.getEndDate())
                      .createdAt(postEntity.getCreatedAt())
                      .writer(postEntity.getWriter())
                      .images(images)
                      .build();
    }

    @Transactional
    @Override
    public PostDto readPostById(Long id) {
        log.info("Post Service's Service Layer :: Call readPostById Method!");

        PostEntity postEntity = postRepository.findPostById(id);
        List<ImageEntity> images = new ArrayList<>();
        List<CommentEntity> comments = new ArrayList<>();

        if(postEntity.getPostType().equals("빌려줄게요")) {
            postEntity.getImages().forEach(i -> {
                String filePath = i.getFilePath();
                i.setFilePath(FileUtil.getFileContent(filePath));

                images.add(i);
            });
        }

        postEntity.getComments().forEach(i -> {
            comments.add(CommentEntity.builder()
                                      .id(i.getId())
                                      .comment(i.getComment())
                                      .writer(i.getWriter())
                                      .createdAt(i.getCreatedAt())
                                      .build());
        });

        return PostDto.builder()
                      .id(postEntity.getId())
                      .userId(postEntity.getUserId())
                      .postType(postEntity.getPostType())
                      .category(postEntity.getCategory())
                      .rentalPrice(postEntity.getRentalPrice())
                      .title(postEntity.getTitle())
                      .content(postEntity.getContent())
                      .startDate(postEntity.getStartDate())
                      .endDate(postEntity.getEndDate())
                      .createdAt(postEntity.getCreatedAt())
                      .writer(postEntity.getWriter())
                      .images(images)
                      .comments(comments)
                      .status(postEntity.getStatus())
                      .build();
    }

    @Transactional
    @Override
    public List<PostDto> getAllPosts() {
        log.info("Post Service's Service Layer :: Call getAllPosts Method!");

        List<String> exceptList = new ArrayList<>();

        exceptList.add("COMPLETE_RENTAL");
        exceptList.add("DELETE_POST");

        Iterable<PostEntity> posts = postRepository.findAllByStatusNotIn(exceptList);
        List<PostDto> postList = new ArrayList<>();

        posts.forEach(v -> {
            List<ImageEntity> images = new ArrayList<>();

            if(v.getPostType().equals("빌려줄게요")) {
                v.getImages().forEach(i -> {
                    String filePath = i.getFilePath();
                    i.setFilePath(FileUtil.getFileContent(filePath));

                    images.add(i);
                });
            }

            postList.add(PostDto.builder()
                                .id(v.getId())
                                .userId(v.getUserId())
                                .postType(v.getPostType())
                                .title(v.getTitle())
                                .createdAt(v.getCreatedAt())
                                .writer(v.getWriter())
                                .images(images)
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
            List<ImageEntity> images = new ArrayList<>();

            if(v.getPostType().equals("빌려줄게요")) {
                v.getImages().forEach(i -> {
                    String filePath = i.getFilePath();
                    i.setFilePath(FileUtil.getFileContent(filePath));

                    images.add(i);
                });

            }

            postList.add(PostDto.builder()
                                .id(v.getId())
                                .userId(v.getUserId())
                                .postType(v.getPostType())
                                .title(v.getTitle())
                                .createdAt(v.getCreatedAt())
                                .writer(v.getWriter())
                                .images(images)
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
            List<ImageEntity> images = new ArrayList<>();

            if(v.getPostType().equals("빌려줄게요")) {
                v.getImages().forEach(i -> {
                    String filePath = i.getFilePath();
                    i.setFilePath(FileUtil.getFileContent(filePath));

                    images.add(i);
                });
            }

            postList.add(PostDto.builder()
                                .id(v.getId())
                                .userId(v.getUserId())
                                .postType(v.getPostType())
                                .title(v.getTitle())
                                .createdAt(v.getCreatedAt())
                                .writer(v.getWriter())
                                .images(images)
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

        postEntity.setStatus(PostStatus.DELETE_POST.name());

        postRepository.save(postEntity);

        return PostDto.builder()
                      .id(postEntity.getId())
                      .status(postEntity.getStatus())
                      .build();
    }

    @Transactional
    @Override
    public Iterable<PostDto> getPostsByKeyword(String keyword) {
        log.info("Post Service's Service Layer :: Call getPostsByKeyword Method!");

        Iterable<PostEntity> posts = postRepository.findByKeywordLike(keyword);
        List<PostDto> postList = new ArrayList<>();

        posts.forEach(v -> {
            List<ImageEntity> images = new ArrayList<>();

            if(v.getPostType().equals("빌려줄게요")) {
                v.getImages().forEach(i -> {
                    String filePath = i.getFilePath();
                    i.setFilePath(FileUtil.getFileContent(filePath));

                    images.add(i);
                });
            }

            postList.add(PostDto.builder()
                                .id(v.getId())
                                .userId(v.getUserId())
                                .postType(v.getPostType())
                                .title(v.getTitle())
                                .createdAt(v.getCreatedAt())
                                .writer(v.getWriter())
                                .images(images)
                                .status(v.getStatus())
                                .build());
        });

        return postList;
    }

    @Transactional
    @Override
    public Iterable<PostDto> getPostsByCategory(String category) {
        log.info("Post Service's Service Layer :: Call getPostsByCategory Method!");

        ArrayList<String> exceptList = new ArrayList<>();

        exceptList.add(PostStatus.COMPLETE_RENTAL.name());
        exceptList.add(PostStatus.DELETE_POST.name());

        Iterable<PostEntity> posts = postRepository.findAllByCategory(
            category,
            exceptList
        );
        List<PostDto> postList = new ArrayList<>();

        posts.forEach(v -> {
            List<ImageEntity> images = new ArrayList<>();

            if(v.getPostType().equals("빌려줄게요")) {
                v.getImages().forEach(i -> {
                    String filePath = i.getFilePath();
                    i.setFilePath(FileUtil.getFileContent(filePath));

                    images.add(i);
                });
            }

            postList.add(PostDto.builder()
                                .id(v.getId())
                                .userId(v.getUserId())
                                .postType(v.getPostType())
                                .title(v.getTitle())
                                .createdAt(v.getCreatedAt())
                                .writer(v.getWriter())
                                .images(images)
                                .status(v.getStatus())
                                .build());
        });

        return postList;
    }

    @Transactional
    @Override
    public void rental(Long id) {
        log.info("Post Service's Service Layer :: Call rental Method!");

        PostEntity entity = postRepository.findPostById(id);

        entity.setStatus(PostStatus.COMPLETE_RENTAL.name());

        postRepository.save(entity);
    }

    @Transactional
    @Override
    public void rollbackPost(Long postId) {
        log.info("Post Service's Service Layer :: Call rollbackPost Method!");

        PostEntity entity = postRepository.findPostById(postId);

        entity.setStatus(PostStatus.READY_RENTAL.name());

        postRepository.save(entity);
    }
}
