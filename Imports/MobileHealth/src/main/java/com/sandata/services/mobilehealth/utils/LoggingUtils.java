package com.sandata.services.mobilehealth.utils;

/**
 * @author huyvo
 * 
 * formats all log messages following a common template 
 * that is easy for searching on Splunk log
 */
public class LoggingUtils {
    private static final String PROJECT_NAME = "MobileHealth";

    /**
     * Constructor
     */
    private LoggingUtils() {
        // empty constructor
    }
    
    /**
     * Calculate execution time by subtracting endTime and startTime in miliseconds
     * 
     * @param startTime
     * @param endTime
     * @return
     */
    public static final String getExecDuration(final long startTime, final long endTime) {
        return (endTime - startTime) + "ms";
    }
    
    /**
     * extends the prefix for log messages at RouteBuilder<br/>
     * example: [MobileHealth] - [Route:ProcessFileRoute] : <b><code>msg</code></b>
     * 
     * @param routeId   the current route for logging
     * @param msg       the message needs to be logged out
     * @return
     */
    public static final String getLogMessageForRoute(String msg) {
        return new StringBuilder()
            .append("[").append(PROJECT_NAME).append("] - [Route:${routeId}] : ")
            .append(msg)
            .toString();
    }
    
    /**
     * extends the prefix for eror log messages at RouteBuilder<br/>
     * example: [MobileHealth] - ERROR.ROUTE - [Route:ProcessFileRoute] : <b><code>msg</code></b>
     * 
     * @param routeId   the current route for logging
     * @param msg       the error message needs to be logged out
     * @return
     */
    public static final String getErrorLogMessageForRoute(String msg) {
        return new StringBuilder()
            .append("[").append(PROJECT_NAME).append("] - ERROR.ROUTE - [Route:${routeId}]: ")
            .append(msg)
            .toString();
    }
    
    /**
     * extends the prefix for log messages at processor<br/>
     * example: [MobileHealth] - [ProcessFolderContextPreparer.prepareFileNameAndPath] : <b><code>msg</code></b> 
     * 
     * @param clazz    the processor class
     * @param method    the processing method
     * @param msg       the message needs to be logged out
     * @return
     */
    public static final String getLogMessageForProcessor(Class<?> clazz, String methodName, String msg) {
        return new StringBuilder()
            .append("[").append(PROJECT_NAME).append("] - ")
            .append("[").append(clazz.getSimpleName()).append(".").append(methodName).append("] : ")
            .append(msg)
            .toString();
    }
    
    /**
     * extends the prefix for error log messages at processor<br/>
     * example: [MobileHealth] - ERROR.PROCESSOR - [ProcessFolderContextPreparer.prepareFileNameAndPath] : <b><code>msg</code></b>
     * 
     * @param clazz    the processor class
     * @param method    the processing method
     * @param msg       the error message needs to be logged out
     * @return
     */
    public static final String getErrorLogMessageForProcessor(Class<?> clazz, String methodName, String msg) {
        return new StringBuilder()
            .append("[").append(PROJECT_NAME).append("] - ERROR.PROCESSOR - ")
            .append("[").append(clazz.getSimpleName()).append(".").append(methodName).append("] : ")
            .append(msg)
            .toString();
    }
}
