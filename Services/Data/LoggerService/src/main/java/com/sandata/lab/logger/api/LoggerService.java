package com.sandata.lab.logger.api;

public interface LoggerService {

    void setUserGuid(String userGuid);
    void log(String process, Long pid, Long thread, String level, String message);
    void log(String userGuid, String process, Long pid, Long thread, String level, String message);
    void log(String userGuid, String host, String process, Long pid, Long thread, String level, String message);
    void info(String process, String message);
    void info(Long pid, String process, String message);
    void warn(String process, String message);
    void warn(Long pid, String process, String message);
    void error(String process, String message);
    void error(Long pid, String process, String message);
    void trace(String process, String message);
    void trace(Long pid, String process, String message);
    void debug(String process, String message);
    void debug(Long pid, String process, String message);
}
