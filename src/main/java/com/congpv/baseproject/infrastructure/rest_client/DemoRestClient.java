package com.congpv.baseproject.infrastructure.rest_client;

import com.congpv.baseproject.core.domain.Post;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "demo-rest-client", url = "https://jsonplaceholder.typicode.com/")
public interface DemoRestClient {

  @RequestMapping(method = RequestMethod.GET, value = "/posts")
  List<Post> getPosts();
}