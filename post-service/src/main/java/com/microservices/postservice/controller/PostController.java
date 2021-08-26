package com.microservices.postservice.controller;

import com.microservices.postservice.dto.CommentDto;
import com.microservices.postservice.dto.PostDto;
import com.microservices.postservice.service.CommentService;
import com.microservices.postservice.service.PostService;
import com.microservices.postservice.vo.RequestCreateComment;
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
    private CommentService commentService;
    private Environment env;

    @Autowired
    public PostController(
        PostService postService,
        CommentService commentService,
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
    public ResponseEntity<?> write(@ModelAttribute RequestWrite postVo) throws Exception {
        log.info("Post Service's Controller Layer :: Call write Method!");

        PostDto postDto = PostDto.builder()
                                 .postType(postVo.getPostType())
                                 .title(postVo.getTitle())
                                 .content(postVo.getContent())
                                 .startDate(postVo.getDate().get(0))
                                 .endDate(postVo.getDate().get(1))
                                 .rentalPrice(postVo.getRentalPrice())
                                 .writer(postVo.getWriter())
                                 .userId(postVo.getUserId())
                                 .multipartFiles(postVo.getImages())
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
                                          .startDate(post.getStartDate())
                                          .endDate(post.getEndDate())
                                          .createdAt(post.getCreatedAt())
                                          .writer(post.getWriter())
                                          .userId(post.getUserId())
                                          .images(post.getImages())
                                          .comments(post.getComments())
                                          .status(post.getStatus())
                                          .build();

        return ResponseEntity.status(HttpStatus.OK)
                             .body(result);
    }

    // Get All Posts
    @GetMapping("/")
    public ResponseEntity<?> getAllPosts() {
        log.info("Post Service's Controller Layer :: Call getAllPosts Method!");

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
                            .startDate(post.getStartDate())
                            .endDate(post.getEndDate())
                            .createdAt(post.getCreatedAt())
                            .writer(post.getWriter())
                            .userId(post.getUserId())
                            .status(post.getStatus())
                            .images(post.getImages())
                            .comments(post.getComments())
                            .build()
                );
            }
        );

        return ResponseEntity.status(HttpStatus.OK)
                             .body(result);
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllPostsByCreate() {
        log.info("Post Service's Controller Layer :: Call getAllPostsByCreate Method!");

        Iterable<PostDto> postList = postService.getAllPostsByCreate();
        List<ResponsePost> result = new ArrayList<>();

        postList.forEach(post -> {
                result.add(
                    ResponsePost.builder()
                                .postId(post.getPostId())
                                .postType(post.getPostType())
                                .title(post.getTitle())
                                .content(post.getContent())
                                .rentalPrice(post.getRentalPrice())
                                .startDate(post.getStartDate())
                                .endDate(post.getEndDate())
                                .createdAt(post.getCreatedAt())
                                .writer(post.getWriter())
                                .userId(post.getUserId())
                                .status(post.getStatus())
                                .images(post.getImages())
                                .comments(post.getComments())
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
                            .startDate(post.getStartDate())
                            .endDate(post.getEndDate())
                            .createdAt(post.getCreatedAt())
                            .writer(post.getWriter())
                            .userId(post.getUserId())
                            .status(post.getStatus())
                            .images(post.getImages())
                            .comments(post.getComments())
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

    @PostMapping("/{postId}/comments")
    public ResponseEntity<?> writeComment(
        @PathVariable("postId") String postId,
        @RequestBody RequestCreateComment comment
    ) {
        log.info("Post Service's Controller Layer :: Call writeComment Method!");

        CommentDto commentDto = CommentDto.builder()
                                          .postId(postId)
                                          .writer(comment.getWriter())
                                          .comment(comment.getComment())
                                          .build();

        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(commentService.writeComment(commentDto));
    }

    @DeleteMapping("/{commentId}/comments")
    public ResponseEntity<?> deleteComment(@PathVariable("commentId") Long commentId) {
        log.info("Post Service's Controller Layer :: Call deleteComment Method!");

        return ResponseEntity.status(HttpStatus.OK)
                             .body(commentService.deleteComment(commentId));
    }
}
