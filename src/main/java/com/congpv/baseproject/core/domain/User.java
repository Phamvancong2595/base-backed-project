package com.congpv.baseproject.core.domain;

import lombok.Data;

@Data
public class User {

  private Long userId;
  private String username;
  private String encryptedPassword;
  private Boolean enabled;
}
