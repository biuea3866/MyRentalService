package com.microservices.postservice.controller;

import com.microservices.postservice.dto.PostDto;
import com.microservices.postservice.service.PostService;
import com.microservices.postservice.vo.RequestWrite;
import com.microservices.postservice.vo.ResponsePost;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
@Slf4j
public class PostController {
    private PostService postService;
    private Environment env;

    @Autowired
    public PostController(
        PostService postService,
        Environment env
    ) {
        this.postService = postService;
        this.env = env;
    }

    @GetMapping("/health_check")
    public String status() {
        return String.format(
        "It's working in Post Service"
            + ", port(local.server.port) =" + env.getProperty("local.server.port")
            + ", port(server.port) =" + env.getProperty("server.port")
        );
    }

    // Using by RequestCreate class, Write Post
    @PostMapping("/write")
    public ResponseEntity<?> write(@ModelAttribute RequestWrite postVo) {
        log.info("Post Service's Controller Layer :: Call write Method!");

        PostDto postDto = PostDto.builder()
                                 .postType(postVo.getPostType())
                                 .title(postVo.getTitle())
                                 .content(postVo.getContent())
                                 .rentalPrice(postVo.getRentalPrice())
                                 .writer(postVo.getWriter())
                                 .userId(postVo.getUserId())
                                 .build();

        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(postService.write(postDto));
    }

    @GetMapping("/{postId}")
    public ResponseEntity<?> readPostByPostId(@PathVariable("postId") String postId) {
        log.info("Post Service's Controller Layer :: Call readPostByPostId Method!");

        PostDto post = postService.readPostByPostId(postId);
        ResponsePost result = ResponsePost.builder()
                                          .postId(post.getPostId())
                                          .postType(post.getPostType())
                                          .title(post.getTitle())
                                          .content(post.getContent())
                                          .rentalPrice(post.getRentalPrice())
                                          .startDate(post.getDate().get(0))
                                          .endDate(post.getDate().get(1))
                                          .createdAt(post.getCreatedAt())
                                          .isLike(post.getIsLike())
                                          .isDislike(post.getIsDislike())
                                          .delYn(post.getDelYn())
                                          .viewCnt(post.getViewCnt())
                                          .writer(post.getWriter())
                                          .userId(post.getUserId())
                                          .status(post.getStatus())
                                          .build();

        return ResponseEntity.status(HttpStatus.OK)
                             .body(result);
    }

    // Get All Posts
    @GetMapping("/")
    public ResponseEntity<?> getAllPosts() {
        log.info("Post Service's Controller Layer :: Call getPosts Method!");

        Iterable<PostDto> postList = postService.getAllPosts();
        List<ResponsePost> result = new ArrayList<>();

        postList.forEach(post -> {
                result.add(
                    ResponsePost.builder()
                        .postId(post.getPostId())
                        .postType(post.getPostType())
                        .title(post.getTitle())
                        .content(post.getContent())
                        .rentalPrice(post.getRentalPrice())
                        .startDate(post.getDate().get(0))
                        .endDate(post.getDate().get(1))
                        .createdAt(post.getCreatedAt())
                        .isLike(post.getIsLike())
                        .isDislike(post.getIsDislike())
                        .delYn(post.getDelYn())
                        .viewCnt(post.getViewCnt())
                        .writer(post.getWriter())
                        .userId(post.getUserId())
                        .status(post.getStatus())
                        .build()
                );
            }
        );

        return ResponseEntity.status(HttpStatus.OK)
                             .body(result);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getPostsByUserId(@PathVariable("userId") String userId) {
        log.info("Post Service's Controller Layer :: Call getPostsByUserId Method!");

        Iterable<PostDto> postList = postService.getPostsByUserId(userId);
        List<ResponsePost> result = new ArrayList<>();

        postList.forEach(post -> {
            result.add(
                ResponsePost.builder()
                            .postId(post.getPostId())
                            .postType(post.getPostType())
                            .title(post.getTitle())
                            .content(post.getContent())
                            .rentalPrice(post.getRentalPrice())
                            .startDate(post.getDate().get(0))
                            .endDate(post.getDate().get(1))
                            .createdAt(post.getCreatedAt())
                            .isLike(post.getIsLike())
                            .isDislike(post.getIsDislike())
                            .delYn(post.getDelYn())
                            .viewCnt(post.getViewCnt())
                            .writer(post.getWriter())
                            .userId(post.getUserId())
                            .status(post.getStatus())
                            .build()
                );
            }
        );

        return ResponseEntity.status(HttpStatus.OK)
                             .body(result);
    }

//    @PostMapping("/rental")
//    public ResponseEntity<?> rental(@RequestBody RequestRental postVo) {
//        log.info("Post Service's Controller Layer :: Call rental Method!");
//
//        return ResponseEntity.status(HttpStatus.OK)
//                             .body(postService.rental(postVo));
//    }

    @PostMapping("/{postId}/delete")
    public ResponseEntity<?> deletePost(@PathVariable("postId") String postId) {
        log.info("Post Service's Controller Layer :: Call deletePost Method!");

        return ResponseEntity.status(HttpStatus.OK)
                             .body(postService.deletePost(postId));
    }
}
