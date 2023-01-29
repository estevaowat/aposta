package com.ewcode.friendsbigball.config.log;

import java.time.LocalDateTime;
import java.util.UUID;

public class LogInfo {
  private LocalDateTime requestDate;
  private String correlationId;

  public LogInfo() {
    correlationId = UUID.randomUUID().toString();
    requestDate = LocalDateTime.now();
  }

  public String getCorrelationId() {
    return correlationId;
  }

  public LocalDateTime getRequestDate() {
    return requestDate;
  }

  public String getCorrelationLog() {
    return "[" + correlationId + "] -> ";
  }
}
