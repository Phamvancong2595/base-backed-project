package com.congpv.baseproject.application.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(Include.NON_NULL)
public class LoginResponse {

  private Boolean success;
  private String message;
  private String accessToken;
  private String tokenType;

}
