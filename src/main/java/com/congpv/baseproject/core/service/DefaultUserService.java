package com.congpv.baseproject.core.service;

import com.congpv.baseproject.core.adapter.UserAdapter;
import com.congpv.baseproject.infrastructure.config.auth.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultUserService implements UserService {
  private final UserAdapter userAdapter;

  @Override
  public CustomUserDetails getUserDetailByUserId(Long userId) {
    return userAdapter.getUserDetailByUserId(userId);
  }

  @Override
  public CustomUserDetails getUserDetailByUsername(String username) {
    return userAdapter.getUserDetailByUsername(username);
  }

  @Override
  public CustomUserDetails loadDefaultUserForFixedTokenAuth() {
    return CustomUserDetails.builder().username("default").build();
  }
}
