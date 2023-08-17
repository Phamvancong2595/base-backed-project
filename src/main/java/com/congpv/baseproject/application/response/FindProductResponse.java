package com.congpv.baseproject.application.response;

import com.congpv.baseproject.core.domain.Product;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FindProductResponse extends BaseResponse{
  private Product product;
}
