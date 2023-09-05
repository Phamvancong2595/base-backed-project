package com.congpv.baseproject.core.service;

import com.congpv.baseproject.core.domain.Post;
import com.congpv.baseproject.infrastructure.shared.exception.PostNotFoundException;
import com.congpv.baseproject.infrastructure.rest_client.DemoRestClient;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

public interface PostService {

  Post getLastPost() throws PostNotFoundException;
}