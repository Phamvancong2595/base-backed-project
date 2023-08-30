package com.congpv.baseproject.core.service;

import com.congpv.baseproject.core.domain.Post;
import com.congpv.baseproject.infrastructure.rest_client.DemoRestClient;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component("PostService")
@RequiredArgsConstructor
public class PostService {

  private final DemoRestClient demoRestClient;

  public Post getLastPost() {
    List<Post> posts = demoRestClient.getPosts();
    if (posts.isEmpty()) {
      return new Post();
    }
    return posts.get(0);
  }
}