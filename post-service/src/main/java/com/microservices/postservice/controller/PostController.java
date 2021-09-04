package com.microservices.postservice.controller;

import com.microservices.postservice.dto.CommentDto;
import com.microservices.postservice.dto.PostDto;
import com.microservices.postservice.message.KafkaProducer;
import com.microservices.postservice.service.CommentService;
import com.microservices.postservice.service.PostService;
import com.microservices.postservice.vo.RequestCreateComment;
import com.microservices.postservice.vo.RequestRental;
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
    private KafkaProducer kafkaProducer;

    @Autowired
    public PostController(
        PostService postService,
        CommentService commentService,
        Environment env,
        KafkaProducer kafkaProducer
    ) {
        this.postService = postService;
        this.commentService = commentService;
        this.env = env;
        this.kafkaProducer = kafkaProducer;
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
    public ResponseEntity<?> write(@RequestBody RequestWrite postVo) throws Exception {
        log.info("Post Service's Controller Layer :: Call write Method!");

        PostDto postDto = null;

        if(postVo.getPostType().equals("빌려줄게요")) {
            postDto = PostDto.builder()
                             .postType(postVo.getPostType())
                             .title(postVo.getTitle())
                             .content(postVo.getContent())
                             .startDate(postVo.getDate().get(0))
                             .endDate(postVo.getDate().get(1))
                             .rentalPrice(postVo.getRentalPrice())
                             .writer(postVo.getWriter())
                             .userId(postVo.getUserId())
//                                     .multipartFiles(postVo.getImages())
                             .build();
        } else {
            postDto = PostDto.builder()
                             .postType(postVo.getPostType())
                             .title(postVo.getTitle())
                             .content(postVo.getContent())
                             .startDate(null)
                             .endDate(null)
                             .rentalPrice(null)
                             .writer(postVo.getWriter())
                             .userId(postVo.getUserId())
            //                                     .multipartFiles(postVo.getImages())
                             .build();
        }

        PostDto post = postService.write(postDto);
        ResponsePost result = ResponsePost.builder()
                                          .id(post.getId())
                                          .postType(post.getPostType())
                                          .title(post.getTitle())
                                          .content(post.getContent())
                                          .rentalPrice(post.getRentalPrice())
                                          .startDate(post.getStartDate())
                                          .endDate(post.getEndDate())
                                          .createdAt(post.getCreatedAt())
                                          .writer(post.getWriter())
                                          .userId(post.getUserId())
//                                        .images(post.getImages())
                                          .comments(post.getComments())
                                          .status(post.getStatus())
                                          .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/{id}/post")
    public ResponseEntity<?> readPostById(@PathVariable("id") Long id) {
        log.info("Post Service's Controller Layer :: Call readPostById Method!");

        PostDto post = postService.readPostById(id);

        return ResponseEntity.status(HttpStatus.OK).body(ResponsePost.builder()
                                                                     .id(post.getId())
                                                                     .postType(post.getPostType())
                                                                     .title(post.getTitle())
                                                                     .content(post.getContent())
                                                                     .rentalPrice(post.getRentalPrice())
                                                                     .startDate(post.getStartDate())
                                                                     .endDate(post.getEndDate())
                                                                     .createdAt(post.getCreatedAt())
                                                                     .writer(post.getWriter())
                                                                     .userId(post.getUserId())
//                                                                     .images(post.getImages())
                                                                     .comments(post.getComments())
                                                                     .status(post.getStatus())
                                                                     .build());
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
                            .id(post.getId())
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
//                            .images(post.getImages())
                            .comments(post.getComments())
                            .build());
        });

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/{status}")
    public ResponseEntity<?> getAllPostsByStatus(@PathVariable("status") String status) {
        log.info("Post Service's Controller Layer :: Call getAllPostsByStatus Method!");

        Iterable<PostDto> postList = postService.getAllPostsByStatus(status);
        List<ResponsePost> result = new ArrayList<>();

        postList.forEach(post -> {
            result.add(ResponsePost.builder()
                                   .id(post.getId())
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
//                                .images(post.getImages())
                                   .comments(post.getComments())
                                   .build());
        });

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/{userId}/posts")
    public ResponseEntity<?> getPostsByUserId(@PathVariable("userId") String userId) {
        log.info("Post Service's Controller Layer :: Call getPostsByUserId Method!");

        log.info("Before receive post data");

        Iterable<PostDto> postList = postService.getPostsByUserId(userId);
        List<ResponsePost> result = new ArrayList<>();

        postList.forEach(post -> {
            result.add(ResponsePost.builder()
                                   .id(post.getId())
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
    //                            .images(post.getImages())
                                   .comments(post.getComments())
                                   .build());
            }
        );

        log.info("After received post data");

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping("/rental")
    public ResponseEntity<?> rental(@RequestBody RequestRental postVo) {
        log.info("Post Service's Controller Layer :: Call rental Method!");

        kafkaProducer.send("rental-topic", postVo);

        return ResponseEntity.status(HttpStatus.OK).body(postVo);
    }

    @PostMapping("/{id}/delete")
    public ResponseEntity<?> deletePost(@PathVariable("id") Long id) {
        log.info("Post Service's Controller Layer :: Call deletePost Method!");

        return ResponseEntity.status(HttpStatus.OK).body(postService.deletePost(id));
    }

    @PostMapping("/{postId}/comments")
    public ResponseEntity<?> writeComment(
        @PathVariable("postId") Long postId,
        @RequestBody RequestCreateComment comment
    ) {
        log.info("Post Service's Controller Layer :: Call writeComment Method!");

        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.writeComment(CommentDto.builder()
                                                                                                    .postId(postId)
                                                                                                    .writer(comment.getWriter())
                                                                                                    .comment(comment.getComment())
                                                                                                    .build()));
    }

    @DeleteMapping("/{id}/comments")
    public ResponseEntity<?> deleteComment(@PathVariable("id") Long id) {
        log.info("Post Service's Controller Layer :: Call deleteComment Method!");

        return ResponseEntity.status(HttpStatus.OK).body(commentService.deleteComment(id));
    }
}
