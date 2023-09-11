package com.congpv.baseproject.infrastructure.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

  @Bean
  public GroupedOpenApi publicApi() {
    return GroupedOpenApi.builder()
        .packagesToScan("com.congpv.baseproject.application")
        .group("demo-app")
        .build();
  }
}
