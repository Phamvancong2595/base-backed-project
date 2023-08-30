package com.congpv.baseproject.application.response;

import com.congpv.baseproject.core.domain.Post;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@JsonInclude(Include.NON_NULL)
public class PostResponse extends BaseResponse {
  private Post post;

}
