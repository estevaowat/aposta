package com.ewcode.friendsbigball.config.log;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;

@Configuration
public class LogConfig {

  @Bean
  @RequestScope
  public LogInfo logInfo() {
    return new LogInfo();
  }
}
