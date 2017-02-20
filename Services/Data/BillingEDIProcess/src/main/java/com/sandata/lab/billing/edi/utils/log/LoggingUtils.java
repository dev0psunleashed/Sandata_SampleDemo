package com.sandata.lab.billing.edi.utils.log;

import com.sandata.lab.billing.edi.utils.constants.App;
import com.sandata.lab.common.utils.string.StringUtil;

/**
 * The utility class is used to build easy-to-search log messages in Splunk
 */
public final class LoggingUtils {

    private LoggingUtils() {
        // no operation
    }

    /**
     * Gets info message in format
     * </p>
     * [APPLICATION_KEYWORD][object.getClass().getSimpleName()][BE_ID=beId]: detailMessage
     * 
     * @param object
     *            the object to get the simple name of the class to log
     * @param beId
     *            the Business Entity ID to log
     * @param detailMessage
     *            the detail message to log
     * @return a formated info message to log
     */
    public static String getLogMessageInfoForBeId(Object object, String beId, String detailMessage) {
        StringBuilder builder = getMessageBuilder(object.getClass().getSimpleName(), null);
        appendBeId(builder, beId);
        appendMessage(builder, detailMessage);

        return builder.toString();
    }

    /**
     * Gets info message in format
     * </p>
     * [APPLICATION_KEYWORD][object.getClass().getSimpleName()]: detailMessage
     * 
     * @param object
     *            the object to get the simple name of the class to log
     * @param detailMessage
     *            the detail message to log
     * @return a formated info message to log
     * @see #getLogMessageInfo(String, String)
     */
    public static String getLogMessageInfo(Object object, String detailMessage) {
        return getLogMessageInfo(object.getClass().getSimpleName(), detailMessage);
    }

    /**
     * Gets info message in format
     * </p>
     * [APPLICATION_KEYWORD][simpleClassName]: detailMessage
     * 
     * @param simpleClassName
     *            the simple name of the class to log
     * @param detailMessage
     *            the detail message to log
     * @return a formated info message to log
     */
    public static String getLogMessageInfo(String simpleClassName, String detailMessage) {
        return getLogMessageInfo(simpleClassName, null, detailMessage);
    }

    /**
     * Gets info message in format
     * </p>
     * [APPLICATION_KEYWORD][object.getClass().getSimpleName()][methodName]: detailMessage
     * 
     * @param object
     *            the object to get the simple name of the class to log
     * @param methodName
     *            the method name to log
     * @param detailMessage
     *            the detail message to log
     * @return a formated info message to log
     * 
     * @see #getErrorMessageInfo(String, String, String)
     */
    public static String getLogMessageInfo(Object object, String methodName, String detailMessage) {
        return getLogMessageInfo(object.getClass().getSimpleName(), methodName, detailMessage);
    }

    /**
     * Gets info message in format
     * </p>
     * [APPLICATION_KEYWORD][simpleClassName][methodName]: detailMessage
     * 
     * @param simpleClassName
     *            the simple name of the class to log
     * @param methodName
     *            the method name to log
     * @param detailMessage
     *            the detail message to log
     * @return a formated info message to log
     */
    public static String getLogMessageInfo(String simpleClassName, String methodName, String detailMessage) {
        StringBuilder builder = getMessageBuilder(simpleClassName, methodName);
        appendMessage(builder, detailMessage);

        return builder.toString();
    }

    /**
     * Gets error message in format
     * </p>
     * [APPLICATION_KEYWORD][object.getClass().getSimpleName()][methodName][ERROR]: detailMessage
     * 
     * @param object
     *            the object to get the simple name of the class to log
     * @param methodName
     *            the method name to log
     * @param detailMessage
     *            the detail message to log
     * @return a formated error message to log
     * 
     * @see #getErrorMessageInfo(String, String, String)
     */
    public static String getErrorMessageInfo(Object object, String methodName, String detailMessage) {
        return getErrorMessageInfo(object.getClass().getSimpleName(), methodName, detailMessage);
    }

    /**
     * Gets error message in format
     * </p>
     * [APPLICATION_KEYWORD][simple class name of specified {@code object}][ERROR]: detailMessage
     * 
     * @param object
     *            the object to get the simple name of the class to log
     * @param detailMessage
     *            the detail message to log
     * @return a formated error message to log
     */
    public static String getErrorMessageInfo(Object object, String detailMessage) {
        return getErrorMessageInfo(object.getClass().getSimpleName(), null, detailMessage);
    }

    /**
     * Gets error message in format
     * </p>
     * [APPLICATION_KEYWORD][simpleClassName][methodName][ERROR]: detailMessage
     * 
     * @param simpleClassName
     *            the simple name of the class to log
     * @param methodName
     *            the method name to log
     * @param detailMessage
     *            the detail message to log
     * @return a formated error message to log
     */
    public static String getErrorMessageInfo(String simpleClassName, String methodName, String detailMessage) {
        StringBuilder builder = getMessageBuilder(simpleClassName, methodName);
        appendError(builder);
        appendMessage(builder, detailMessage);

        return builder.toString();
    }

    private static StringBuilder getMessageBuilder() {
        return new StringBuilder().append("[").append(App.APPLICATION_KEYWORD).append("]");
    }

    private static StringBuilder getMessageBuilder(String simpleClassName, String methodName) {
        StringBuilder builder = getMessageBuilder();
        appendWithBracket(builder, simpleClassName);
        appendWithBracket(builder, methodName);

        return builder;
    }

    private static void appendWithBracket(StringBuilder builder, String name) {
        if (!StringUtil.IsNullOrEmpty(name)) {
            builder.append("[").append(name).append("]");
        }
    }

    private static void appendBeId(StringBuilder builder, String beId) {
        if (!StringUtil.IsNullOrEmpty(beId)) {
            builder.append("[BE_ID=").append(beId).append("]");
        }
    }

    private static void appendError(StringBuilder builder) {
        builder.append("[ERROR]");
    }

    private static void appendMessage(StringBuilder builder, String detailMessage) {
        builder.append(": ").append(detailMessage);
    }
}