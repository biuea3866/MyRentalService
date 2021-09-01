package com.microservices.authservice.clients;

import com.microservices.authservice.vo.ResponsePost;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="post-service")
public interface PostClient {
    @GetMapping("/{userId}/posts")
    public List<ResponsePost> getPosts(@PathVariable("userId") String userId);
}
