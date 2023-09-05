package com.congpv.baseproject.core.service;

import com.congpv.baseproject.core.domain.Post;
import com.congpv.baseproject.infrastructure.rest_client.DemoRestClient;
import com.congpv.baseproject.infrastructure.shared.exception.PostNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

@Component("PostService")
@RequiredArgsConstructor
@Slf4j
public class DefaultPostService implements PostService {

  private final DemoRestClient demoRestClient;

  @Override
  @Retryable(
      value = PostNotFoundException.class,
      maxAttemptsExpression = "${retry.maxAttempts}",
      backoff = @Backoff(delayExpression = "${retry.backOffDelay}"))
  public Post getLastPost() throws PostNotFoundException {
    List<Post> posts = demoRestClient.getPosts();
    if (posts.isEmpty()) {
      log.info("No Posts Found");
      throw new PostNotFoundException();
    }
    return posts.get(0);
  }
}
