package com.sandata.lab.eligibility.utils.log;

import com.sandata.lab.common.utils.string.StringUtil;
import com.sandata.lab.eligibility.utils.constants.App;

/**
 * The utility class is used to build easy-to-search log messages in Splunk
 */
public final class LoggingUtils {

    private LoggingUtils() {
        // no operation
    }

    public static String getLogMessageInfoForBeId(Object object, String beId, String detailMessage) {
        StringBuilder builder = getMessageBuilder(object.getClass().getSimpleName(), null);
        appendBeId(builder, beId);
        appendMessage(builder, detailMessage);

        return builder.toString();
    }

    public static String getLogMessageInfo(Object object, String detailMessage) {
        return getLogMessageInfo(object.getClass().getSimpleName(), detailMessage);
    }

    public static String getLogMessageInfo(String simpleClassName, String detailMessage) {
        return getLogMessageInfo(simpleClassName, null, detailMessage);
    }

    public static String getLogMessageInfo(Object object, String methodName, String detailMessage) {
        return getLogMessageInfo(object.getClass().getSimpleName(), methodName, detailMessage);
    }

    public static String getLogMessageInfo(String simpleClassName, String methodName, String detailMessage) {
        StringBuilder builder = getMessageBuilder(simpleClassName, methodName);
        appendMessage(builder, detailMessage);

        return builder.toString();
    }

    public static String getErrorMessageInfo(Object object, String methodName, String detailMessage) {
        return getErrorMessageInfo(object.getClass().getSimpleName(), methodName, detailMessage);
    }

    public static String getErrorMessageInfo(Object object, String detailMessage) {
        return getErrorMessageInfo(object.getClass().getSimpleName(), null, detailMessage);
    }

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
