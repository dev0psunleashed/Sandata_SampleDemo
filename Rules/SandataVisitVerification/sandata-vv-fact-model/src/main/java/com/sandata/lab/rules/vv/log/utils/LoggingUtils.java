package com.sandata.lab.rules.vv.log.utils;

/**
 * 
 */


/**
 * 
 * 
 * This utility class is used to log messages when some conditions are met in route
 * 
 * <p>
 * For some reasons, using .when() to log messages in route definition does not
 * work
 * 
 * @author thanh.le
 *
 * @date Dec 23, 2015
 * 
 * 
 */
public final class LoggingUtils {

	private final static String APPLICATION_KEYWORD = "ARCHER_VV";
	public final static String SUB_APP_CALL_IMPORT_KEYWORD = "SANDATA_VV_IMPORT_CALLS";
	public final static String SUB_APP_CALL_MATCHING_SERVICE_KEYWORD = "SANDATA_VV_CALL_MATCHING_SERVICE";
	public final static String SUB_APP_VV_DATA_SERVICE = "SANDATA_VV_DATA_SERVICE";
	public final static String SUB_APP_VISIT_EXCEPTION_KEYWORD = "SANDATA_VV_VISIT_EXCEPTION";
	
	public final static String SUB_APP_VISIT_VALIDATION_SERVICE_KEYWORD = "SANDATA_VV_VISIT_VALIDATION";
	
    private LoggingUtils() {
        super();
    }
    
    
    
    /**
     * extends the prefix for log messages at RouteBuilder 
     * @param routeId   the current route for logging
     * @param msg       the message needs to be logged out
     * @return
     */
    public static final String getLogMessageForRoute(String subAppKeyword , String msg) {
        return new StringBuilder()
            .append(getLogPrefix(subAppKeyword)).append("] - [Route:${routeId}] : ")
            .append(msg)
            .toString();
    }
    
    /**
     * extends the prefix for error log messages at RouteBuilder 
     * @param routeId   the current route for logging
     * @param msg       the error message needs to be logged out
     * @return
     */
    public static final String getErrorLogMessageForRoute(String subAppKeyword ,String msg) {
        return new StringBuilder()
            .append("[").append(getLogPrefix(subAppKeyword)).append("] - ERROR.ROUTE - [Route:${routeId}]: ")
            .append(msg)
            .toString();
    }
    
    /**
     * extends the prefix for log messages at processor 
     * @param clazz    the processor class
     * @param method    the processing method
     * @param msg       the message needs to be logged out
     * @return
     */
    public static final String getLogMessageForProcessor(String subAppKeyword, Class<?> clazz, String methodName, String msg) {
        return new StringBuilder()
            .append("[").append(getLogPrefix(subAppKeyword)).append("] - ")
            .append("[").append(clazz.getSimpleName()).append(".").append(methodName).append("] : ")
            .append(msg)
            .toString();
    }
    
    
    
    /**
     * extends the prefix for error log messages at processor 
     * @param clazz    the processor class
     * @param method    the processing method
     * @param msg       the error message needs to be logged out
     * @return
     */
    public static final String getErrorLogMessageForProcessor(String subAppKeyword,Class<?> clazz, String methodName, String msg) {
        return new StringBuilder()
            .append("[").append(getLogPrefix(subAppKeyword)).append("] - ERROR.PROCESSOR - ")
            .append("[").append(clazz.getSimpleName()).append(".").append(methodName).append("] : ")
            .append(msg)
            .toString();
    }
    
    

    public static StringBuilder getLogPrefix(String subAppKeyword) {
    	StringBuilder builder = new StringBuilder("[" + APPLICATION_KEYWORD + "]");
        builder.append("[" + subAppKeyword +"]");
        
        return builder;
    }

}
