package com.ewcode.friendsbigball.infra.log;

import com.ewcode.friendsbigball.app.log.LogBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class Logger4JImplTest {
    private final LogBuilder logBuilder = new LogBuilder().className(Logger4JImplTest.class.getName());

    @Test
    @DisplayName("Should log an info message")
    void logInfoMessage() {
        Logger4JImpl logger = Mockito.mock(Logger4JImpl.class);
        logBuilder.message("This is a info test");
        logger.info(logBuilder);
        Mockito.verify(logger, Mockito.times(1)).info(logBuilder);
    }

    @Test
    @DisplayName("Should log an error message")
    void logErrorMessage() {
        Logger4JImpl logger = Mockito.mock(Logger4JImpl.class);
        logBuilder.message("This is a info test");
        logger.error(logBuilder);
        Mockito.verify(logger, Mockito.times(1)).error(logBuilder);
    }

    @Test
    @DisplayName("Should log an warn message")
    void logWarnMessage() {
        Logger4JImpl logger = Mockito.mock(Logger4JImpl.class);
        logger.warn(logBuilder);
        Mockito.verify(logger, Mockito.times(1)).warn(logBuilder);
    }
}