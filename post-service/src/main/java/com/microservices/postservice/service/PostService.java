package com.microservices.postservice.service;

import com.microservices.postservice.dto.PostDto;

public interface PostService {
    Long write(PostDto postDto) throws Exception;

    PostDto readPostByPostId(String postId);

    Iterable<PostDto> getAllPosts();

    Iterable<PostDto> getPostsByUserId(String userId);

    PostDto deletePost(String postId);
}
