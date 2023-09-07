package com.congpv.baseproject.core.adapter;

import com.congpv.baseproject.infrastructure.config.CacheConfig;
import com.congpv.baseproject.infrastructure.config.auth.CustomUserDetails;
import com.congpv.baseproject.infrastructure.shared.utils.EncryptedPasswordUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DefaultUserAdapter implements UserAdapter {

  @Override
  @Cacheable(
      cacheNames = "getUserDetailByUserId",
      unless = "#result == null",
      cacheManager = CacheConfig.CACHE_REDIS
  )
  public CustomUserDetails getUserDetailByUserId(Long userId) {
    return CustomUserDetails.builder()
        .userId(111L)
        .username("congpv")
        .encryptedPassword(EncryptedPasswordUtils.encryptPassword("test"))
        .departmentId(1)
        .tel("0988458602")
        .build();
  }

  @Override
  @Cacheable(
      cacheNames = "getUserDetailByUsername",
      unless = "#result == null",
      cacheManager = CacheConfig.CACHE_REDIS
  )
  public CustomUserDetails getUserDetailByUsername(String username) {
    return CustomUserDetails.builder()
        .userId(111L)
        .username("congpv")
        .encryptedPassword(EncryptedPasswordUtils.encryptPassword("test"))
        .departmentId(1)
        .tel("0988458602")
        .build();
  }
}
