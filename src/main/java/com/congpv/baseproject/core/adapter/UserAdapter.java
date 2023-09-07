package com.congpv.baseproject.core.adapter;

import com.congpv.baseproject.infrastructure.config.auth.CustomUserDetails;

public interface UserAdapter {
  CustomUserDetails getUserDetailByUserId(Long userId);
  CustomUserDetails getUserDetailByUsername(String username);

}
