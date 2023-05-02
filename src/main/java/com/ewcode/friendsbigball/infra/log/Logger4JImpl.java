package com.ewcode.friendsbigball.infra.log;

import com.ewcode.friendsbigball.app.log.LogBuilder;
import com.ewcode.friendsbigball.app.log.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Logger4JImpl implements Logger {
    @Bean
    public Logger getLogger() {
        return new Logger4JImpl();
    }

    @Override
    public void info(LogBuilder logBuilder) {
        org.apache.logging.log4j.Logger logger = LogManager.getLogger(logBuilder.getClassName());
        logger.info(LogBuilder.template, logBuilder.getCorrelationId(), logBuilder.getMessage());
    }
    @Override
    public void error(LogBuilder logBuilder) {
        org.apache.logging.log4j.Logger logger = LogManager.getLogger(logBuilder.getClassName());
        logger.error(LogBuilder.template, logBuilder.getCorrelationId(), logBuilder.getMessage());
    }
    @Override
    public void warn(LogBuilder logBuilder) {
        org.apache.logging.log4j.Logger logger = LogManager.getLogger(logBuilder.getClassName());
        logger.warn(LogBuilder.template, logBuilder.getCorrelationId(), logBuilder.getMessage());
    }

}
