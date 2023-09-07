package com.congpv.baseproject.core.service;

import com.congpv.baseproject.infrastructure.config.auth.CustomUserDetails;
import org.springframework.stereotype.Component;

@Component
public interface UserService {

  CustomUserDetails getUserDetailByUserId(Long userId);

  CustomUserDetails getUserDetailByUsername(String username);

  CustomUserDetails loadDefaultUserForFixedTokenAuth();
}
