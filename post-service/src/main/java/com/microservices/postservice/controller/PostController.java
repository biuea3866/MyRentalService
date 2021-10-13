package com.microservices.postservice.controller;

import com.microservices.postservice.dto.CommentDto;
import com.microservices.postservice.dto.PostDto;
import com.microservices.postservice.entity.CommentEntity;
import com.microservices.postservice.message.KafkaProducer;
import com.microservices.postservice.service.CommentService;
import com.microservices.postservice.service.ImageService;
import com.microservices.postservice.service.PostService;
import com.microservices.postservice.states.PostStatus;
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
    private ImageService imageService;
    private Environment env;
    private KafkaProducer kafkaProducer;

    @Autowired
    public PostController(
        PostService postService,
        CommentService commentService,
        ImageService imageService,
        Environment env,
        KafkaProducer kafkaProducer
    ) {
        this.postService = postService;
        this.commentService = commentService;
        this.imageService = imageService;
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
    public ResponseEntity<?> write(@ModelAttribute RequestWrite postVo) throws Exception {
        log.info("Post Service's Controller Layer :: Call write Method!");

        PostDto postDto = null;

        if(postVo.getPostType().equals("빌려줄게요")) {
            postDto = PostDto.builder()
                             .postType(postVo.getPostType())
                             .category(postVo.getCategory())
                             .title(postVo.getTitle())
                             .content(postVo.getContent())
                             .startDate(postVo.getDate().get(0))
                             .endDate(postVo.getDate().get(1))
                             .rentalPrice(postVo.getRentalPrice())
                             .writer(postVo.getWriter())
                             .status(PostStatus.READY_RENTAL.name())
                             .userId(postVo.getUserId())
                             .multipartFiles(postVo.getImages())
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
                             .status(PostStatus.REQUEST_RENTAL.name())
                             .userId(postVo.getUserId())
                             .multipartFiles(postVo.getImages())
                             .build();
        }

        PostDto post = postService.write(postDto);
        ResponsePost result = ResponsePost.builder()
                                          .id(post.getId())
                                          .postType(post.getPostType())
                                          .category(post.getCategory())
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

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
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
                            .category(post.getCategory())
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
                            .build());
        });

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<?> readPostById(@PathVariable("id") Long id) {
        log.info("Post Service's Controller Layer :: Call readPostById Method!");

        PostDto post = postService.readPostById(id);

        return ResponseEntity.status(HttpStatus.OK).body(ResponsePost.builder()
                                                                     .id(post.getId())
                                                                     .postType(post.getPostType())
                                                                     .category(post.getCategory())
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
                                                                     .build());
    }

    @GetMapping("/posts/{status}")
    public ResponseEntity<?> getAllPostsByStatus(@PathVariable("status") String status) {
        log.info("Post Service's Controller Layer :: Call getAllPostsByStatus Method!");

        Iterable<PostDto> postList = postService.getAllPostsByStatus(status);
        List<ResponsePost> result = new ArrayList<>();

        postList.forEach(post -> {
            result.add(ResponsePost.builder()
                                   .id(post.getId())
                                   .postType(post.getPostType())
                                   .category(post.getCategory())
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
                                   .category(post.getCategory())
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
                                   .build());
            }
        );

        log.info("After received post data");

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/posts/keyword/{keyword}")
    public ResponseEntity<?> getPostsByKeyword(@PathVariable("keyword") String keyword) {
        log.info("Post Service's Controller Layer :: Call getPostsByKeyword Method!");

        Iterable<PostDto> postList = postService.getPostsByKeyword(keyword);
        List<ResponsePost> result = new ArrayList<>();

        postList.forEach(post -> {
                result.add(ResponsePost.builder()
                                       .id(post.getId())
                                       .postType(post.getPostType())
                                       .category(post.getCategory())
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
                                       .build());
               }
        );

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/posts/category/{category}")
    public ResponseEntity<?> getPostsByCategory(@PathVariable("category") String category) {
        log.info("Post Service's Controller Layer :: Call getPostsByCategory Method!");

        Iterable<PostDto> postList = postService.getPostsByCategory(category);
        List<ResponsePost> result = new ArrayList<>();

        postList.forEach(post -> {
                result.add(ResponsePost.builder()
                                       .id(post.getId())
                                       .postType(post.getPostType())
                                       .category(post.getCategory())
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
                                       .build());
            }
        );

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping("/rental")
    public ResponseEntity<?> rental(@RequestBody RequestRental postVo) {
        log.info("Post Service's Controller Layer :: Call rental Method!");

        kafkaProducer.send("rental-topic", postVo);

        postService.rental(postVo.getPostId());

        return ResponseEntity.status(HttpStatus.OK).body("Success rental");
    }

    @PostMapping("/{id}/delete")
    public ResponseEntity<?> deletePost(@PathVariable("id") Long id) {
        log.info("Post Service's Controller Layer :: Call deletePost Method!");

        return ResponseEntity.status(HttpStatus.OK).body(postService.deletePost(id));
    }

    @PostMapping("/comments")
    public ResponseEntity<?> writeComment(@RequestBody RequestCreateComment comment) {
        log.info("Post Service's Controller Layer :: Call writeComment Method!");

        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.writeComment(CommentDto.builder()
                                                                                                    .postId(comment.getPostId())
                                                                                                    .writer(comment.getWriter())
                                                                                                    .comment(comment.getComment())
                                                                                                    .build()));
    }

    @DeleteMapping("/{id}/comments")
    public ResponseEntity<?> deleteComment(@PathVariable("id") Long id) {
        log.info("Post Service's Controller Layer :: Call deleteComment Method!");

        return ResponseEntity.status(HttpStatus.OK).body(commentService.deleteComment(id));
    }

    @PostMapping("/rollback/{postId}")
    public ResponseEntity<?> rollbackPost(@PathVariable("postId") Long postId) {
        log.info("Post Service's Controller Layer :: Call rollbackPost Method!");

        postService.rollbackPost(postId);

        return ResponseEntity.status(HttpStatus.OK).body("Successfully rollback!");
    }
}
