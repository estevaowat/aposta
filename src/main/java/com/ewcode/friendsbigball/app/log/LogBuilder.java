package com.ewcode.friendsbigball.app.log;

import java.util.UUID;

public class LogBuilder {
    public static final String template = "[{}] -> {}";
    private String message;
    private String className;
    private final String correlationId;
    public LogBuilder() {
        correlationId = UUID.randomUUID().toString();
    }
    public String getCorrelationId() {
        return correlationId;
    }
    public String getMessage() {
        return message;
    }
    public LogBuilder message(String message) {
        this.message = message;
        return this;
    }
    public String getClassName() {
        return className;
    }
    public LogBuilder className(String className) {
        this.className = className;
        return this;
    }
}
