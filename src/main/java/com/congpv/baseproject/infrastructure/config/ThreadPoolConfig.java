package com.congpv.baseproject.infrastructure.config;

import java.net.InetAddress;
import java.net.UnknownHostException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class ThreadPoolConfig {

  @Bean("threadPoolTaskExecutor")
  public TaskExecutor getAsyncTaskExecutor() throws UnknownHostException {
    return buildPoolTaskExecutor("Demo-Async");
  }

  @Bean("threadPoolTaskExecutor2")
  public TaskExecutor getAsyncTaskExecutor2() throws UnknownHostException {
    return buildPoolTaskExecutor("Demo-Async2");
  }

  public TaskExecutor buildPoolTaskExecutor(String prefix) throws UnknownHostException {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    InetAddress localhost = InetAddress.getLocalHost();
    int maxPoolSize = Runtime.getRuntime().availableProcessors() * 8;
    executor.setCorePoolSize(10);
    executor.setQueueCapacity(200);
    executor.setMaxPoolSize(maxPoolSize);
    executor.setWaitForTasksToCompleteOnShutdown(true);
    executor.setThreadNamePrefix(prefix + localhost.getHostName() + "-");
    return executor;
  }
}
