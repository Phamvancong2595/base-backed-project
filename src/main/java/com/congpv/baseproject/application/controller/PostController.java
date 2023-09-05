package com.congpv.baseproject.application.controller;

import com.congpv.baseproject.application.response.PostResponse;
import com.congpv.baseproject.core.domain.Post;
import com.congpv.baseproject.core.service.PostService;
import com.congpv.baseproject.infrastructure.shared.exception.PostNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController extends BaseController {
  private final PostService postService;
  @GetMapping(value = "/get-last")
  public PostResponse getLastPost() throws PostNotFoundException {
    Post post = postService.getLastPost();
    return PostResponse.builder().post(post).success(true).build();
  }
}
