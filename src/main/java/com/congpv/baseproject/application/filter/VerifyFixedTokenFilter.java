package com.congpv.baseproject.application.filter;

import com.congpv.baseproject.core.service.UserService;
import com.congpv.baseproject.infrastructure.config.auth.CustomUserDetails;
import com.congpv.baseproject.infrastructure.shared.constants.AppConstants;
import java.io.IOException;
import java.util.Optional;
import java.util.Set;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

public class VerifyFixedTokenFilter extends OncePerRequestFilter {

  //  verify fixed tokens
  @Value("${verified_tokens}")
  private Set<String> tokenSet;
  @Autowired
  private UserService userService;

  //verify fixed tokens
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    try {
      if (verifyToken(getRequestToken(request))) {
        CustomUserDetails user = userService.loadDefaultUserForFixedTokenAuth();
        UsernamePasswordAuthenticationToken authentication =
            new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
      }
    } catch (Exception e) {
      logger.error("Invalid fixed token", e);
    }
    filterChain.doFilter(request, response);
  }

  private boolean verifyToken(Optional<String> token) {
    boolean verified = false;
    if (token.isPresent()) {
      verified = tokenSet.contains(token.get());
    }
    return verified;
  }

  private Optional<String> getRequestToken(HttpServletRequest request) {
    return Optional.ofNullable(request.getHeader(AppConstants.FIX_TOKEN_HEADER));
  }

  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
    return !request.getRequestURI().startsWith("/api");
  }
}
