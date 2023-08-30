package com.congpv.baseproject.application.controller;

import com.congpv.baseproject.core.domain.Post;
import com.congpv.baseproject.core.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/posts")
@RequiredArgsConstructor
public class PostController extends BaseController {
  private final PostService postService;
  @GetMapping(value = "/get-last")
  public Post getLastPost() {
    return postService.getLastPost();
  }
}
