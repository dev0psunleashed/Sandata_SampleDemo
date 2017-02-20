package com.sandata.services.mobilehealth.utils;

import java.sql.Timestamp;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.sandata.up.commons.exception.SandataRuntimeException;
import com.sandata.services.mobilehealth.data.models.StatusCode;

public class CommonUtils {
    
    private static final Pattern passwordPattern =
            Pattern.compile("(?i)(password)(=)([^&,;]*)");
    private static final Pattern usernamePattern =
            Pattern.compile("(?i)(username)(=)([a-zA-Z0-9_\\-\\.]+)");
    private static final Pattern usernamePattern2 =
            Pattern.compile("([a-zA-Z0-9_\\-\\.]+)(@)([a-zA-Z0-9_\\-\\.]+)(:)([0-9]+)");
    public static final String PLACEHOLDER = "****";
    
    /**
     * Default constructor
     */
    private CommonUtils() {
        
    }
    
    /**
     * hide username and password info on log message
     * @param msg message
     * @return a new string replace user name and password info
     */
    public static final String hideUsernameAndPassword(String msg){
        String newMsg = hideUsername(msg);
        return hidePassword(newMsg);
    }
    
    /**
     * hide password in a string. 
     * Example: password=123456 -> password=****.
     * @param message
     * @return 
     */
    public static String hidePassword(final String message){
        return replaceAll(message, passwordPattern, "password", "$1$2" + PLACEHOLDER);
    }
    
    /**
     * hide the username  in a string.
     * Example 1: username=abcde -> username=**** .
     * Example 2: user.name@email.com:22 -> ****@email.com:22 .
     * @param message
     * @return
     */
    public static final String hideUsername(final String message){
        String newMsg = replaceAll(message, usernamePattern, "username", "$1$2" + PLACEHOLDER);
        return replaceAll(newMsg, usernamePattern2, "", PLACEHOLDER + "$2$3$4$5");
    }
    
    private static final String replaceAll(String text, Pattern pattern, String key, String replacePattern){
        if(text != null && !text.isEmpty() && StringUtils.containsIgnoreCase(text, key)){
            Matcher matcher = pattern.matcher(text);
            return matcher.replaceAll(replacePattern);
        }
        return text;
    }
    

    public static LocalDateTime toLocalDateTime(Timestamp dateTime) {
       return toLocalDateTime(dateTime, null);
    }

    public static LocalDateTime toLocalDateTime(Object dateTimeObject, String pattern) {
        if (dateTimeObject == null) {
            return  null;
        } else if (dateTimeObject instanceof String) {
            DateTimeFormatter dtf = DateTimeFormat.forPattern(pattern);
            return dtf.parseLocalDateTime(dateTimeObject.toString());
        } else if (dateTimeObject instanceof Timestamp) {
            return new LocalDateTime(((Timestamp)dateTimeObject).getTime());
        } else {
            throw new SandataRuntimeException("Can not convert object to Joda LocalDateTime");
        }
    }

    public static LocalDate toLocalDate(String dateString) {
        return toLocalDate(dateString, Constants.DATE_PATTERN);
    }

    public static LocalDate toLocalDate(Object dateObject, String pattern) {
        if (dateObject == null) {
            return  null;
        } else if (dateObject instanceof String) {
            DateTimeFormatter dtf = DateTimeFormat.forPattern(pattern);
            return dtf.parseLocalDate(dateObject.toString());
        } else if (dateObject instanceof Timestamp) {
            return new LocalDate(((Timestamp)dateObject).getTime());
        } else {
            throw new SandataRuntimeException("Can not convert object to Joda LocalDate");
        }
    }

    public static Integer parseIntString(Object intString) {
        if (intString == null || "".equals(intString.toString().trim())) {
            return null;
        } else {
            return Integer.parseInt(intString.toString());
        }
    }

    public static StatusCode parseStatusCode(final String statusCodeString) {
        if (statusCodeString == null) {
            return null;
        } else {
            return StatusCode.valueOf(statusCodeString);
        }
    }
    public static boolean isNotBlank(String word){
        if(null == word || word.isEmpty()){
            return false;
        }
        return true;
    }
    public static Timestamp toSqlTimestamp(LocalDateTime dateTime){
        if(dateTime == null){
            return null;
        }
        return new Timestamp(dateTime.toDate().getTime());
    }
    
    /**
     * only get ssn with 4 ending characters
     * @param ssn
     * @return
     */
    public static final String hideSSN(String ssn) {
        if (StringUtils.isBlank(ssn)) {
            return "";
        }
        
        if (ssn.length() > 4) {
            return "*****" + ssn.substring(ssn.length() - 4);
        } else {
            return ssn;
        }
    }
}
