package com.ewcode.friendsbigball.app.log;

public interface Logger {
    void info(LogBuilder logBuilder);
    void error(LogBuilder logBuilder);
    void warn(LogBuilder logBuilder);

}
